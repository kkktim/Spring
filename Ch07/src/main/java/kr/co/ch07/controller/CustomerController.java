package kr.co.ch07.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.ch07.service.CustomerService;
import kr.co.ch07.vo.CustomerVo;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService service;
	//등록
	@GetMapping("/customer/register")
	public String register() {
		return "/customer/register";
	}
	@PostMapping("/customer/register")
	public String register(CustomerVo cv) {
		service.insertCustomer(cv);
		return "redirect:/customer/list";
	}
	
	//리스트
	@GetMapping("/customer/list")
	public String list(Model model, @RequestParam(defaultValue = "0") int result) {
		List<CustomerVo> customers = service.selectCustomers();
		model.addAttribute("customers", customers);
		model.addAttribute("result", result);
		
		return "/customer/list";
	}
	
	//수정
	@GetMapping("/customer/modify")
	public String modify(int custid, Model model) {
		Optional<CustomerVo> customer = service.selectCustomer(custid);
		model.addAttribute("customer", customer.get());
		return "/customer/modify";
	}
	@PostMapping("/customer/modify")
	public String modify(CustomerVo cv, Model model) {
		CustomerVo customer = service.updateCustomer(cv);
		
		int result = 0;
		if(customer != null ) {
			result = 1;
		}
		
		return "redirect:/customer/list?result="+result;
	}
	
	//삭제
	@GetMapping("/customer/delete")
	public String delete(int custid) {
		service.deleteCustomer(custid);
		return "redirect:/customer/list";
	}
}
