package kr.co.ch08.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.ch08.service.User2Service;
import kr.co.ch08.vo.User2Vo;

@Controller
public class User2Controller {
	@Autowired
	private User2Service service;
	
	//로그인
	@GetMapping("/user2/login")
	public String login(HttpSession sess) {
		//로그인 확인
		User2Vo user = (User2Vo) sess.getAttribute("sessUser");
		if(user == null) {
			return "/user2/login";
		}else {
			return "redirect:/user2/loginSuccess";
		}
	}
	
	@PostMapping("/user2/login")
	public String login(User2Vo uv, HttpSession sess) {
		User2Vo user = service.selectUser(uv);
		if(user == null) {
			return "redirect:/user2/login?success=100";
		}else {
			//세션 처리
			sess.setAttribute("sessUser", user);
			return "redirect:/user2/loginSuccess";
		}
	}
	//로그인 성공
	@GetMapping("/user2/loginSuccess")
	public String loginSuccess(HttpSession sess) {
		//로그인 여부 확인
		User2Vo user = (User2Vo) sess.getAttribute("sessUser");
		if(user == null) {
			return "redirect:/user2/login?success=101";
		}else {
			return "/user2/loginSuccess";
		}
	}
	//로그아웃
	@GetMapping("/user2/logout")
	public String logout(HttpSession sess) {
		//세션 해제
		sess.invalidate();
		return "redirect:/user2/login?success=103";
	}
	
	@GetMapping("/user2/register")
	public String register(HttpSession sess) {
		User2Vo user = (User2Vo) sess.getAttribute("sessUser");
		if(user == null) {
			return "/user2/register";
		}else {
			return "redirect:/user2/loginSuccess";
		}
	}
	@PostMapping("/user2/register")
	public String register(User2Vo uv) {
		service.insertUser(uv);
		return "redirect:/user2/login";
	}
	
}
