package kr.co.sboard1.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.google.gson.JsonObject;

import kr.co.sboard1.service.UserService;
import kr.co.sboard1.vo.TermsVo;
import kr.co.sboard1.vo.UserVo;

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
	//login-전송
	@PostMapping("/user/login")
	public String login(UserVo uv, Model model) {
		
		UserVo user = service.selectUser(uv);
		
		if(user == null) {
			//로그인 실패
			return "redirect:/user/login?success=100";
		}else {
			//로그인 성공
			model.addAttribute("sessUser", user);
			return "redirect:/article/list";
		}
		
	}
	//logout
	@GetMapping("/user/logout")
	public String logout(SessionStatus status) {
		//sess.invalidate();
		status.setComplete(); 
		return "redirect:/user/login?success=103";
	}
	//terms
	@GetMapping("/user/terms")
	public String terms(Model model) {
		TermsVo terms = service.selectTerms();
		model.addAttribute("terms", terms);
		return "/user/terms";
	}
	//등록
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
	
	//validation
	//checkUid
	@ResponseBody
	@GetMapping("/user/checkUid")
	public Map<String, Integer> checkUid(String uid) {
		int result = service.CountUserUid(uid);
		//Spring Boot의 기본 json 라이브러리 JackSon으로 자바의 자료구조를 자동으로 Json객체로 출력함
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		return map;
	}
	//checkNick
	@ResponseBody
	@GetMapping("/user/checkNick")
	public String checkNick(String nick) {
		int result = service.CountUserNick(nick);
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		return json.toString();
	}
	
}
