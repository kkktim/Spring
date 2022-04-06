package kr.co.ch06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.ch06.service.UserService;
import kr.co.ch06.vo.UserVo;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/user/register")
	public String register() {
		return "/user/register";
	}
	
	@PostMapping("/user/register")
	public String register(UserVo vo) {
		
		//등록요청
		service.insertUser(vo);
		
		return "redirect:/user/register";
	}
	
	@GetMapping("/user/list")
	public String list(Model model) {
		//데이터요청
		List<UserVo> users = service.selectUsers();
		model.addAttribute("users", users);
		
		return "/user/list";
	}
	
	@GetMapping("/user/modify")
	public String modify(String uid, Model model) {
		
		//수정데이터요청
		UserVo user = service.selectUser(uid);
		model.addAttribute("user", user);
		
		return "/user/modify";
	}

	@PostMapping("/user/modify")
	public String modify(UserVo vo) {
		
		//수정요청
		service.updateUser(vo);
		
		return "redirect:/user/list";
	}
	
	@GetMapping("/user/delete")
	public String delete(String uid) {
		service.deleteUser(uid);
		return "redirect:/user/list";
	}
}
