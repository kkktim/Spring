package kr.co.ch07.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch07.persistence.CustomerRepo;
import kr.co.ch07.vo.CustomerVo;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepo repo;
	
	public void insertCustomer(CustomerVo cv) {
		repo.save(cv);
	}
	public Optional<CustomerVo> selectCustomer(int custid) {
		return repo.findById(custid);
	}
	public List<CustomerVo> selectCustomers() {
		return repo.findAll();
	}
	public CustomerVo updateCustomer(CustomerVo cv) {
		CustomerVo result = repo.save(cv);
		return result;
	}
	public void deleteCustomer(int custid) {
		repo.deleteById(custid);
	}
	
}
