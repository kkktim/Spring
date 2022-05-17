package kr.co.kmarket.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.CartVo;
import kr.co.kmarket.vo.CategoriesVo;
import kr.co.kmarket.vo.MemberVo;
import kr.co.kmarket.vo.OrderVo;
import kr.co.kmarket.vo.ProductVo;

@SessionAttributes("sessMember")
@Controller
public class ProductController {
	@Autowired
	private ProductService service;
	
	@ModelAttribute("sessMember")
	public MemberVo setMemberVo() {
		return null;
	}
	
	@GetMapping("/product/search")
	public String search() {
		return "/product/search";
	}
	//장바구니
	@GetMapping("/product/cart")
	public String cart(@ModelAttribute("sessMember")MemberVo sessMember, Model model) {
		if(sessMember == null) {
			return "redirect:/member/login";
		}
		model.addAttribute("carts", service.selectCarts(sessMember.getUid()));
		return "/product/cart";
	}
	
	//post 전송 jsonData 테스트 - 크롬웹스토어 : talend 설치, 주소 http:// (s 없음)
	@ResponseBody
	@PostMapping("/product/cart")
	public Map<String, Integer> cart(CartVo cv) {
		int result = service.insertCart(cv);
		Map<String, Integer> jsonData = new HashMap<>();
		jsonData.put("result", result);
		return jsonData;
	}
	@ResponseBody
	@GetMapping("/product/cartDelete")
	public Map<String, Integer> cartDelete(int[] cids) {
		int result = service.deleteCart(cids);
		Map<String , Integer> map = new HashMap<>();
		map.put("result", result);
		return map;
	}
	
	@GetMapping("/product/list")
	public String list(ProductVo pv, Model model, 
			@PageableDefault(page = 0, size = 7, sort = "pid", direction = Sort.Direction.DESC) Pageable pageable) {
		int start = 0;
		int order = pv.getOrder();
		pv.setStart(start);
		pv.setOrder(order);
//		String order = "sold";
		
		List<ProductVo> products = service.selectProducts(pv);
		CategoriesVo cates = service.selectCateTitles(pv);
		
		model.addAttribute("products", products);
		model.addAttribute("cates", cates);
		model.addAttribute("order", order);
		
//		service.selectProducts(pageable, cates, order);
		
//		model.addAttribute("products");
		
		return "/product/list";
	}
	@GetMapping("/product/view")
	public String view(Model model, int pid) {
		
		ProductVo product = service.selectProduct(pid);
		CategoriesVo cates = service.selectCateTitles(product);
		
		model.addAttribute("product", product);
		model.addAttribute("cates", cates);
		
		return "/product/view";
	}
	
	//주문하기 - ORDER
	@GetMapping("/product/order")
	public String order(int oid, Model model) {
		List<OrderVo> orders =  service.selectOrders(oid);
		model.addAttribute("orders", orders);
		model.addAttribute("order", orders.get(0));
		return "/product/order";
	}
	@ResponseBody
	@PostMapping("/product/order")
	public Map<String, Integer> order(OrderVo ov) {
		//주문장 등록
		int oid = service.insertOrder(ov);
		//개별상품등록
		int[] counts = ov.getCounts();
		int i = 0;
		for(int pid : ov.getPids()) {
			service.insertOrderDetail(oid, pid, counts[i]);
			i++;
		}
		
		Map<String, Integer> map = new HashMap<>();
		map.put("result", oid);
		return map;
	}
	
	//결제 완료 - COMPLETE
	@GetMapping("/product/complete")
	public String complete(int oid, Model model) {
		List<OrderVo> orders = service.selectOrderComplete();
		
		model.addAttribute("orders", orders);
		model.addAttribute("order", orders.get(0));
		
		return "/product/complete";
	}
	@PostMapping("/product/complete")
	public Map<String, Integer> complete(OrderVo ov) {
		int result = service.updateOrder(ov);
		int oid = ov.getOid();
		Map<String, Integer> map = new HashMap<>();
		map.put("result", oid);
		return map;
	}
	
}
