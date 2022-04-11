package kr.co.ch07.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.ch07.service.UserService;
import kr.co.ch07.vo.UserVo;


@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	//등록
	@GetMapping("/user/register")
	public String register() {
		return "/user/register";
	}
	@PostMapping("/user/register")
	public String register(UserVo uv) {
		service.insertUser(uv);
		return "redirect:/user/list";
	}

	//리스트
	@GetMapping("/user/list")
	public String list(Model model, String sort, Integer type) {
		List<UserVo> users = null;
		if(sort != null) {
			if(sort.equals("name")) {
				users = service.selectUsersOrderByName();
			}else if(sort.equals("age") && type == 1) {
				users = service.selectUsersOrderByAgeAsc();
			}else if(sort.equals("age") && type == 2) {
				users = service.selectUsersOrderByAgeDesc();
			}else if(sort.equals("age") && type == 3) {
				users = service.selectUserUnderAge30();
			}
		}else {
			users = service.selectUsers(); 
		}
		model.addAttribute("users", users);
		
		return "/user/list";
	}
	
	//수정
	@GetMapping("/user/modify")
	public String modify(String uid, Model model) {
		UserVo user = service.selectUser(uid);
		model.addAttribute("user", user);
		return "/user/modify";
	}
	@PostMapping("/user/modify")
	public String modify(UserVo uv) {
		service.updateUser(uv);
		return "redirect:/user/list";
	}
	
	//삭제
	@GetMapping("/user/delete")
	public String delete(String uid) {
		service.deleteUser(uid);
		return "redirect:/user/list";
	}
	
}
