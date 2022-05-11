package kr.co.kmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.co.kmarket.service.MainService;
import kr.co.kmarket.vo.CategoriesVo;
import kr.co.kmarket.vo.MemberVo;
import kr.co.kmarket.vo.ProductVo;

@SessionAttributes("sessMember")
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
}
