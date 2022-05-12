package kr.co.ch08.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.ch08.service.User2Service;
import kr.co.ch08.vo.User2Vo;

//세션 어노테이션
@SessionAttributes("sessUser")
@Controller
public class User3Controller {
	@Autowired
	private User2Service service;
	
	//처음 sessUser 값을 초기화 하는 메서드
	@ModelAttribute("sessUser")
	public User2Vo setUser2Vo() {
		return null;
	}
	
	//로그인
	@GetMapping("/user3/login")
	public String login(@ModelAttribute("sessUser") User2Vo sessUser) {
		if(sessUser == null) {
			return "/user3/login";
		}else {
			return "redirect:/user3/loginSuccess";
		}
	}
	//로그인 확인 - 세션어노테이션
	@PostMapping("/user3/login")
	public String login(User2Vo uv, Model model) {
		User2Vo user = service.selectUser(uv);
		if(user == null) {
			return "redirect:/user3/login?success=100";
		}else {
			//최초 세션 값
			//model - 컴포넌트와 뷰 탬플릿 간 매개변수 전송
			model.addAttribute("sessUser", user);
			return "redirect:/user3/loginSuccess";
		}
	}
	//로그인 성공
	@GetMapping("/user3/loginSuccess")
	public String loginSuccess(@ModelAttribute("sessUser") User2Vo sessUser) {
		//어노테이션 했기 때문에 - @ModelAttribute("sessUser") 내용 데이터 받음
		//겟으로 바로 success 페이지 요청하면 sessUser 값이 null 이 아니므로, null로 sessUser를 초기화하는 메서드 추가
		if(sessUser == null) {
			return "redirect:/user3/login?success=101";
		}else {
			return "/user3/loginSuccess";
		}
	}
	//로그아웃
	@GetMapping("/user3/logout")
	public String logout(SessionStatus status) {
		//세션 해제 - SessionStatus status
		//@SessionAttributes로 설정한 세션 해제
		status.setComplete();
		return "redirect:/user3/login?success=103";
	}

	//회원가입
	@GetMapping("/user3/register")
	public String register(@ModelAttribute("sessUser") User2Vo sessUser) {
		if(sessUser == null) {
			return "/user3/register";
		}else {
			return "redirect:/user3/loginSuccess";
		}

	}
	@PostMapping("/user3/register")
	public String register(@ModelAttribute("sessUser") User2Vo sessUser, User2Vo uv) {
		service.insertUser(uv);
		return "redirect:/user3/login";
	}
}
