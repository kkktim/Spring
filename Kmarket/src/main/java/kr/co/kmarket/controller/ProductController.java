package kr.co.kmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.ProductVo;

@Controller
public class ProductController {
	@Autowired
	private ProductService service;
	
	@GetMapping("/product/search")
	public String search() {
		return "/product/search";
	}
	@GetMapping("/product/cart")
	public String cart() {
		return "/product/cart";
	}
	@GetMapping("/product/complete")
	public String complete() {
		return "/product/complete";
	}
	@GetMapping("/product/list")
	public String list(ProductVo pv, Model model) {
		int start = 0;
		int order = 1;
		pv.setStart(start);
		pv.setOrder(order);
		
		List<ProductVo> products = service.selectProducts(pv);
		
		model.addAttribute("products", products);
		
		return "/product/list";
	}
	@GetMapping("/product/order")
	public String order() {
		return "/product/order";
	}
	@GetMapping("/product/view")
	public String view() {
		return "/product/view";
	}
}
