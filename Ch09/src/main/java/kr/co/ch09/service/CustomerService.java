package kr.co.ch09.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch09.persistence.CustomerRepo;
import kr.co.ch09.vo.CustomerVo;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepo repo;
	
	public void insertCustomer(CustomerVo cv) {
		repo.save(cv);
	}
	public void selectCustomer() {}
	public List<CustomerVo> selectCustomers() {
		return repo.findAll();
	}
	public void updateCustomer(CustomerVo cv) {
		repo.save(cv);
	}
	public void deleteCustomer(int custid) {
		repo.deleteById(custid);
	}
	
}
