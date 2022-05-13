package kr.co.farmstory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.co.farmstory.vo.UserVo;

@SessionAttributes("sessUser")
@Controller
public class IntroductionController {

	//세션 초기화 코드
	@ModelAttribute("sessUser")
	public UserVo setUserVo() {
		return null;
	}
	
	@GetMapping("/introduction/hello")
	public String hello() {
		return "/introduction/hello";
	}
	
	@GetMapping("/introduction/direction")
	public String direction() {
		return "/introduction/direction";
	}
}