package kr.co.ch06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.ch06.service.MemberService;
import kr.co.ch06.vo.MemberVo;


@Controller
public class MemberController {
	@Autowired
	private MemberService service;
	
	//register
	@GetMapping("/member/register")
	public String register() {
		return "/member/register";
	}
	
	@PostMapping("/member/register")
	public String register(MemberVo mv) {
		service.insertMember(mv);
		return "redirect:/member/register";
	}
	
	//list
	@GetMapping("/member/list")
	public String list(Model model) {
		List<MemberVo> members = service.selectMembers();
		model.addAttribute("members", members);
		return "/member/list";
	}
	
	//modify
	@GetMapping("/member/modify")
	public String modify(String uid, Model model) {
		MemberVo member = service.selectMember(uid);
		model.addAttribute("member", member);
		return "/member/modify";
	}
	@PostMapping("/member/modify")
	public String modify(MemberVo mv) {
		service.updateMember(mv);
		return "redirect:/member/list";
	}
	
	//delete
	@GetMapping("/member/delete")
	public String delete(String uid) {
		service.deleteMember(uid);
		return "redirect:/member/list";
	}
}
