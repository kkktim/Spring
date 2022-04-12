package kr.co.ch08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	//index
	@GetMapping(value = {"/", "/index"})
	public String index() {
		return "/index";
	}
	
	//templates
	@GetMapping("/admin/success")
	public String adminSuccess() {
		return "/admin/success";
	}
	@GetMapping("/guest/success")
	public String guestSuccess() {
		return "/guest/success";
	}
	@GetMapping("/manager/success")
	public String managerSuccess() {
		return "/manager/success";
	}
	@GetMapping("/member/success")
	public String memberSuccess() {
		return "/member/success";
	}
}
