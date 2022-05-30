package kr.co.kmarket.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.co.kmarket.service.MainService;
import kr.co.kmarket.vo.CategoriesVo;
import kr.co.kmarket.vo.MemberVo;
import kr.co.kmarket.vo.ProductVo;
import kr.co.kmarket.vo.SearchVo;

@SessionAttributes({"sessMember", "sessSearchedList"})
@Controller
public class MainController {
	@Autowired
	private MainService service;
	
	@ModelAttribute("sessMember")
	public MemberVo setMemberVo() {
		return null;
	}
	
	@GetMapping(value = {"/", "/index"})
	public String index(@ModelAttribute("sessMember")MemberVo sessMember, Model model) {
		
		List<ProductVo> productHit = service.selectMainProducts("hit");
		List<ProductVo> productRecommend= service.selectMainProducts("score");
		List<ProductVo> productNew = service.selectMainProducts("rdate");
		List<ProductVo> productDiscount = service.selectMainProducts("discount");
		List<ProductVo> productBest = service.selectMainProducts("sold");
		
		model.addAttribute("productHit", productHit);
		model.addAttribute("productRecommend", productRecommend);
		model.addAttribute("productNew", productNew);
		model.addAttribute("productDiscount", productDiscount);
		model.addAttribute("productBest", productBest);
		
		return "/index";
	}
	
	@ResponseBody
	@GetMapping("/getCategories")
	public List<CategoriesVo> selectCategories(){
		return service.selectCategories();
	}
	
	
	//SEARCH
	@GetMapping("/product/search")
	public String search(Model model, String keyword, String orderby, String pg) {
		//페이지 처리
		int currentPage = service.getCurrentPage(pg);
		int start = service.getLimitStart(currentPage);
		
		int order = Integer.parseInt(orderby);
		List<ProductVo> products = service.selectKeyword(keyword, order, start);
		
		model.addAttribute("products", products);
		
		int total = products.get(0).getTotal();
		int lastPageNum = service.getLastPageNum(total);
		int pageStartNum = service.getPageStartNum(total, start);
		int groups[] = service.getPageGroup(currentPage, lastPageNum);
						
		model.addAttribute("sessSearchedList", products);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageStartNum", pageStartNum);
		model.addAttribute("groups", groups);
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("product", products.get(0));
		model.addAttribute("order", order);
		
		return "/product/search";
	}
	
	@PostMapping("/product/search")
	public String search(Model model, String pg, 
						@ModelAttribute("sessSearchedList")List<ProductVo> sessSearchedList,
						SearchVo sv) {
		
		String chk = sv.getChk();
		
		int total = 0;
		
		if(chk.equals("1")) {
			//결과 내 재검색 - 제품 이름만 keyword인 제품 재검색
			List<ProductVo> searchedList = sessSearchedList.stream()
					.filter(h -> h.getName().contains(sv.getKeyword()))
					.collect(Collectors.toList());
			
			//total 필드를 재검색 된 제품 수량으로 바꿔주기
			searchedList.stream().forEach(i -> i.setTotal(searchedList.size()));
			
			model.addAttribute("products", searchedList);
			model.addAttribute("product", searchedList.get(0));
			total = searchedList.size();
		}
		if(chk.equals("2")) {
			//결과 내 재검색 - 제품 설명만 keyword인 제품 재검색
			List<ProductVo> searchedList = sessSearchedList.stream()
					.filter(h -> h.getDesc().contains(sv.getKeyword()))
					.collect(Collectors.toList());
			
			//total 필드를 재검색 된 제품 수량으로 바꿔주기
			searchedList.stream().forEach(i -> i.setTotal(searchedList.size()));
			
			model.addAttribute("products", searchedList);
			model.addAttribute("product", searchedList.get(0));
			total = searchedList.size();
		}
		if(chk.equals("3")) {
			//결과 내 재검색 - 제품 가격이 min max 사이의 제품 재검색
			int min = Integer.parseInt(sv.getMin());
			int max = Integer.parseInt(sv.getMax());
			
			List<ProductVo> searchedList = sessSearchedList.stream()
					.filter(h -> h.getSalePrice() > min && h.getSalePrice() < max)
					.collect(Collectors.toList());
			
			//total 필드를 재검색 된 제품 수량으로 바꿔주기
			searchedList.stream().forEach(i -> i.setTotal(searchedList.size()));
			
			//정렬
//			searchedList.stream().sorted(Comparator.comparingInt(ProductVo::getPrice))
//			.collect(Collectors.toList());
			
			model.addAttribute("products", searchedList);
			model.addAttribute("product", searchedList.get(0));
			total = searchedList.size();
		}

		//페이지 처리
		int currentPage = service.getCurrentPage(pg);
		int start = service.getLimitStart(currentPage);
		
		int lastPageNum = service.getLastPageNum(total);
		int pageStartNum = service.getPageStartNum(total, start);
		int groups[] = service.getPageGroup(currentPage, lastPageNum);
								
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageStartNum", pageStartNum);
		model.addAttribute("groups", groups);

		model.addAttribute("keyword", sv.getKeyword());
		model.addAttribute("order", sv.getOrderby());
		
		return "/product/search";
	}
}
