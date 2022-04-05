package kr.co.ch04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Sub3Controller {
	
	@GetMapping("/sub3/hello")
	public String hello() {
		return "/sub3/hello";
	}
	
	@GetMapping("/sub3/welcome")
	public String welcome() {
		return "/sub3/welcome";
	}
	
	@GetMapping("/sub3/greeting")
	public String greeting() {
		return "/sub3/greeting";
	}
}
