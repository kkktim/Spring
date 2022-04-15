package kr.co.ch09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ch09.service.CustomerService;
import kr.co.ch09.vo.CustomerVo;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService service;
	//list
	@GetMapping("/customer")
	public List<CustomerVo> list() {
		return service.selectCustomers();
	}
	//register
	@PostMapping("/customer")
	public List<CustomerVo> register(CustomerVo cv) {
		service.insertCustomer(cv);
		return service.selectCustomers();
	}
	//modify
	@PutMapping("/customer/{custid}")
	public List<CustomerVo> modify(@PathVariable("custid") String custid, CustomerVo cv) {
		
		System.out.println("custid : "+custid);
		
		cv.setCustid(Integer.parseInt(custid));
		service.updateCustomer(cv);
		return service.selectCustomers();
	}
	
	//delete
	@DeleteMapping("/customer/{custid}")
	public List<CustomerVo> delete(@PathVariable("custid") int custid) {
		service.deleteCustomer(custid);
		return service.selectCustomers();
	}
}
