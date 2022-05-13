package kr.co.ch06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.ch06.service.CustomerService;
import kr.co.ch06.vo.CustomerVo;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	//register
	@GetMapping("/customer/register")
	public String register() {
		return "/customer/register";
	}
	
	@PostMapping("/customer/register")
	public String register(CustomerVo cv) {
		service.insertCustomer(cv);
		return "redirect:/customer/register";
	}
	
	//list
	@GetMapping("/customer/list")
	public String list(Model model) {
		List<CustomerVo> customers = service.selectCustomers();
		model.addAttribute("customers", customers);
		return "/customer/list";
	}
	
	//modify
	@GetMapping("/customer/modify")
	public String modify(int custid, Model model) {
		CustomerVo customer = service.selectCustomer(custid);
		model.addAttribute("customer", customer);
		return "/customer/modify";
	}
	@PostMapping("/customer/modify")
	public String modify(CustomerVo cv) {
		service.updateCustomer(cv);
		return "redirect:/customer/list";
	}
	
	//delete
	@GetMapping("/customer/delete")
	public String delete(int custid) {
		service.deleteCustomer(custid);
		return "redirect:/customer/list";
	}
}
