package kr.co.kmarket.admin.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kmarket.admin.service.AdminProductService;
import kr.co.kmarket.admin.vo.Cate1Vo;
import kr.co.kmarket.admin.vo.Cate2Vo;
import kr.co.kmarket.admin.vo.ProductVo;

@Controller
public class AdminProdudctController {
	@Autowired
	private AdminProductService service;
	
	@GetMapping("/admin/product/list")
	public String list() {
		return "/admin/product/list";
	}
	
	//등록
	@GetMapping("/admin/product/register")
	public String register() {
		return "/admin/product/register";
	}
	@PostMapping("/admin/product/register")
	public String register(ProductVo pv, HttpServletRequest req) {
		String ip = req.getRemoteAddr();
		pv.setIp(ip);
		//상품 썸네일 업로드
		ProductVo pvo = service.uploadThumbnail(pv);
		
		//상품 테이블 등록
		service.insertProduct(pvo);
		return "redirect:/admin/product/register";
	}
	@ResponseBody
	@GetMapping("/admin/product/cate1")
	public List<Cate1Vo> cate1() {
		return service.selectCate1();
	}
	@ResponseBody
	@GetMapping("/admin/product/cate2/{cate1}")
	public List<Cate2Vo> cate2(@PathVariable("cate1") int cate1) {
		return service.selectCate2(cate1);
	}
	
}
