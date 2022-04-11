package kr.co.ch07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.ch07.service.EmployeeService;
import kr.co.ch07.vo.EmployeeVo;

@Controller
public class EmployeeController {

	private EmployeeService service;
	
	@GetMapping("/employee/register")
	public String register() {
		return "/employee/register";
	}
	@PostMapping("/employee/register")
	public String register(EmployeeVo ev) {
//		EmployeeVo result = service.insertEmployee(ev);
//		System.out.println("result : "+result);
		service.insertEmployee(ev);
		System.out.println(ev);
		return "redirect:/employee/list";
	}
}
