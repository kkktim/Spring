package kr.co.ch04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/*
 * 날짜 : 2022/04/05
 * 이름 : 강태호
 * 내용 : Ch04.Spring MVC 실습
 */
@Controller
public class MainController {
	
	@GetMapping("/hello")   //주소
	public String hello() {
		return "/hello";   //view page
	}

	@GetMapping("/welcome")
	public String welcome() {
		return "/welcome";   //view page
	}
	
	@GetMapping("/greeting")
	public String greeting() {
		return "/greeting";   //view page
	}

	@GetMapping("/redirect")
	public String redirect() {
		return "redirect:/sub1/hello";   //view page
	}
	@GetMapping("/forward")
	public String forward() {
		return "forward:/sub2/hello";   //view page
	}
	
	
}
