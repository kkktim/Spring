package kr.co.sboard1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.co.sboard1.vo.UserVo;

@SessionAttributes("sessUser")
@Controller
public class MainController {
	
	//처음 sessUser값을 초기화하는 메서드
	@ModelAttribute("sessUser")
	public UserVo setUserVo() {
		return null;
	}
	
	@GetMapping(value = {"", "/index"})
	public String index(@ModelAttribute("sessUser") UserVo sessUser) {
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}else {
			return "forward:/article/list";
		}
		
	}
}
