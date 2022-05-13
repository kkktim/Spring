package kr.co.ch06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.ch06.service.EmployeeService;
import kr.co.ch06.vo.EmployeeVo;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	//등록
	@GetMapping("/employee/register")
	public String register() {
		return "/employee/register";
	}
	@PostMapping("/employee/register")
	public String register(EmployeeVo ev) {
		service.insertEmployee(ev);
		return "redirect:/employee/register";
	}
	//리스트
	@GetMapping("/employee/list")
	public String list(Model model) {
		List<EmployeeVo> workers = service.selectEmployees();
		model.addAttribute("workers", workers);
		return "/employee/list";
	}
	//수정
	@GetMapping("/employee/modify")
	public String modify(String uid, Model model) {
		EmployeeVo employee = service.selectEmployee(uid);
		model.addAttribute("employee", employee);
		return "/employee/modify";
	}
	@PostMapping("/employee/modify")
	public String modify(EmployeeVo ev) {
		service.updateEmployee(ev);
		return "redirect:/employee/list";
	}
	//삭제
	@GetMapping("/employee/delete")
	public String delete(String uid) {
		service.deleteEmployee(uid);
		return "redirect:/employee/delete";
	}
}
