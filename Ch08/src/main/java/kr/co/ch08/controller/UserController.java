package kr.co.ch08.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.ch08.service.UserService;
import kr.co.ch08.vo.UserVo;

@Controller
public class UserController {
	@Autowired
	private UserService service;
	//로그인
	@GetMapping("/user/login")
	public String login() {
		return "/user/login";
	}
	//로그인-성공
	@GetMapping("/user/loginSuccess")
	public String loginSuccess() {
		return "/user/loginSuccess";
	}
	
	//로그아웃은 Security 에서 설정
	
	//
	@GetMapping("/user/register")
	public String register() {
		return "/user/register";
	}
	@PostMapping("/user/register")
	public String register(UserVo uv) {
		service.insertUser(uv);
		return "redirect:/user/login";
	}
}
