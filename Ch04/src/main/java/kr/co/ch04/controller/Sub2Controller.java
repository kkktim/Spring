package kr.co.ch04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Sub2Controller {

	@GetMapping("/sub2/hello")
	public String hello() {
		return "/sub2/hello";
	}
	
	@GetMapping("/sub2/welcome")
	public String welcome() {
		return "/sub2/welcome";
	}
	
	@GetMapping("/sub2/greeting")
	public String greeting() {
		return "/sub2/greeting";
	}
}
