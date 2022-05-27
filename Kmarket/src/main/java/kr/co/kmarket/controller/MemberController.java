package kr.co.kmarket.controller;

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

import kr.co.kmarket.service.MemberService;
import kr.co.kmarket.vo.MemberVo;
import kr.co.kmarket.vo.TermsVo;

@SessionAttributes("sessMember")
@Controller
public class MemberController {
	@Autowired
	private MemberService service;
	
	@ModelAttribute("sessMember")
	public MemberVo setMemberVo() {
		return null;
	}
	
	@GetMapping("/member/join")
	public String join() {
		return "/member/join";
	}
	//구매회원 가입
	@GetMapping("/member/signup")
	public String signup(Model model) {
		
		TermsVo terms = service.selectTerms();
		model.addAttribute("terms", terms);
		return "/member/signup";
	}
	//seller 가입
	@GetMapping("/member/signup-seller")
	public String signupSeller(Model model) {
			
		TermsVo terms = service.selectTerms();
		model.addAttribute("terms", terms);
		return "/member/signup-seller";
	}
	
	//로그인 
	@GetMapping("/member/login")
	public String login() {
		return "/member/login";
	}
	@PostMapping("/member/login")
	public String login(MemberVo mv, Model model) {
		MemberVo member = service.selectMember(mv);
		if(member == null) {
			return "redirect:/memeber/login?success=100";
		}else {
			model.addAttribute("sessMember", member);
			return "redirect:/";
		}
	}
	//로그아웃
	@GetMapping("/member/logout")
	public String logout(SessionStatus member) {
		member.setComplete();
		return "redirect:/";
	}
	
	
	
	@GetMapping("/member/register")
	public String register() {
		return "/member/register";
	}
	
	@PostMapping("/member/register")
	public String register(MemberVo vo, HttpServletRequest req) {
		
		String ip = req.getRemoteAddr();
		vo.setIp(ip);
		service.insertMember(vo);
		
		return "redirect:/member/login";
	}
	
	@GetMapping("/member/register-seller")
	public String registerSeller() {
		return "/member/register-seller";
	}
	
	@PostMapping("/member/register-seller")
	public String registerSeller(MemberVo vo, HttpServletRequest req) {
		
		String ip = req.getRemoteAddr();
		vo.setIp(ip);
		service.insertSeller(vo);
		return "redirect:/member/login";
	}
	
	
	// 중복체크
	@ResponseBody // 뷰 포워드 하지 않고 클라이언트로 전송 어노테이션
	@GetMapping("/member/checkUid")
	public Map<String, Integer> checkUid(String uid) {
		
		int result = service.CountSellerUid(uid);
		
		// Spring Boot의 기본 json 라이브러리 Jackson으로 자바 자료구조를 자동으로 Json 객체로 출력됨
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		
		return map;
	}
	
	@ResponseBody // 뷰 포워드 하지 않고 클라이언트로 전송 어노테이션
	@GetMapping("/member/checkCompany")
	public Map<String, Integer> checkCompany(String company) {
		
		int result = service.CountSellerCompany(company);
		
		// Spring Boot의 기본 json 라이브러리 Jackson으로 자바 자료구조를 자동으로 Json 객체로 출력됨
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		
		return map;
	}
	
	@ResponseBody // 뷰 포워드 하지 않고 클라이언트로 전송 어노테이션
	@GetMapping("/member/checkTel")
	public Map<String, Integer> checkTel(String tel) {
		
		int result = service.CountSellerTel(tel);
		
		// Spring Boot의 기본 json 라이브러리 Jackson으로 자바 자료구조를 자동으로 Json 객체로 출력됨
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		
		return map;
	}
	@ResponseBody // 뷰 포워드 하지 않고 클라이언트로 전송 어노테이션
	@GetMapping("/member/checkFax")
	public Map<String, Integer> checkFax(String fax) {
		
		int result = service.CountSellerFax(fax);
		
		// Spring Boot의 기본 json 라이브러리 Jackson으로 자바 자료구조를 자동으로 Json 객체로 출력됨
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		
		return map;
	}
	@ResponseBody // 뷰 포워드 하지 않고 클라이언트로 전송 어노테이션
	@GetMapping("/member/checkBizRegNum")
	public Map<String, Integer> checkBizRegNum(String bizRegNum) {
		
		int result = service.CountSellerBizRegNum(bizRegNum);
		
		// Spring Boot의 기본 json 라이브러리 Jackson으로 자바 자료구조를 자동으로 Json 객체로 출력됨
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		
		return map;
	}
	@ResponseBody // 뷰 포워드 하지 않고 클라이언트로 전송 어노테이션
	@GetMapping("/member/checkCommRepNum")
	public Map<String, Integer> checkCommRepNum(String commRepNum) {
		
		int result = service.CountSellerCommRepNum(commRepNum);
		
		// Spring Boot의 기본 json 라이브러리 Jackson으로 자바 자료구조를 자동으로 Json 객체로 출력됨
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		
		return map;
	}
	@ResponseBody // 뷰 포워드 하지 않고 클라이언트로 전송 어노테이션
	@GetMapping("/member/checkManagerHp")
	public Map<String, Integer> checkManagerHp(String managerHp) {
		
		int result = service.CountSellerManagerHp(managerHp);
		
		// Spring Boot의 기본 json 라이브러리 Jackson으로 자바 자료구조를 자동으로 Json 객체로 출력됨
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		
		return map;
	}
	@ResponseBody // 뷰 포워드 하지 않고 클라이언트로 전송 어노테이션
	@GetMapping("/member/checkHp")
	public Map<String, Integer> checkHp(String hp) {
		
		int result = service.CountHp(hp);
		
		// Spring Boot의 기본 json 라이브러리 Jackson으로 자바 자료구조를 자동으로 Json 객체로 출력됨
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		
		return map;
	}
	@ResponseBody // 뷰 포워드 하지 않고 클라이언트로 전송 어노테이션
	@GetMapping("/member/checkEmail")
	public Map<String, Integer> checkEmail(String email) {
		
		int result = service.CountSellerEmail(email);
		
		// Spring Boot의 기본 json 라이브러리 Jackson으로 자바 자료구조를 자동으로 Json 객체로 출력됨
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		
		return map;
	}
}
