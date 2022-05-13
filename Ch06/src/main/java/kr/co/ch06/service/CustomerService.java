package kr.co.ch06.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch06.persistence.CustomerDao;
import kr.co.ch06.vo.CustomerVo;

@Service
public class CustomerService {
	@Autowired
	private CustomerDao dao;
	//등록
	public void insertCustomer(CustomerVo cv) {
		dao.insertCustomer(cv);
	}
	//수정-불러오기
	public CustomerVo selectCustomer(int custid) {
		return dao.selectCustomer(custid);
	}
	//목록
	public List<CustomerVo> selectCustomers() {
		List<CustomerVo> customers = dao.selectCustomers();
		return customers;
	}
	//수정
	public void updateCustomer(CustomerVo cv) {
		dao.updateCustomer(cv);
	}
	
	public void deleteCustomer(int custid) {
		dao.deleteCustomer(custid);
	}
	
}
