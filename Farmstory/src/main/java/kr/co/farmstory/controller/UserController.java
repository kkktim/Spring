package kr.co.farmstory.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.farmstory.service.UserService;
import kr.co.farmstory.vo.TermsVo;
import kr.co.farmstory.vo.UserVo;

@SessionAttributes("sessUser")
@Controller
public class UserController {
	@Autowired
	private UserService service;
	
	//login
	@GetMapping("/user/login")
	public String login() {
		return "/user/login";
	}
	@PostMapping("/user/login")
	public String login(UserVo uv, Model model) {
		UserVo user = service.selectUser(uv);
		if(user == null) {
			return "redirect:/user/login?success=100";
		}else {
			model.addAttribute("sessUser", user);
			return "redirect:/index";
		}
	}
	//logout
	@GetMapping("/user/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:/index?success=103";
	}
	
	
	//회원가입 - terms
	@GetMapping("/user/terms")
	public String terms(Model model) {
		TermsVo terms = service.selectTerms();
		model.addAttribute("terms", terms);
		return "/user/terms";
	}
	
	//회원가입 - register
	@GetMapping("/user/register")
	public String register() {
		return "/user/register";
	}
	@PostMapping("/user/register")
	public String register(UserVo uv, HttpServletRequest req) {
		String regip = req.getRemoteAddr();
		uv.setRegip(regip);
		service.insertUser(uv);
		return "redirect:/user/login";
	}
	
	//VALIDATION - 중복체크
	@ResponseBody
	@GetMapping("/user/checkUid")
	public Map<String, Integer> checkUid(String uid) {
		int result = service.countUserUid(uid);
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		return map;
	}
	
	@ResponseBody
	@GetMapping("/user/checkNick")
	public Map<String, Integer> checkNick(String nick) {
		int result = service.countUserNick(nick);
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		return map;
	}

	@ResponseBody
	@GetMapping("/user/checkEmail")
	public Map<String, Integer> checkEmail(String email) {
		int result = service.countUserEmail(email);
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		return map;
	}
	
	@ResponseBody
	@GetMapping("/user/checkHp")
	public Map<String, Integer> checkHp(String hp) {
		int result = service.countUserHp(hp);
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		return map;
	}
	
}