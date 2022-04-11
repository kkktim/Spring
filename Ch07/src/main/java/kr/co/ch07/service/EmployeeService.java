package kr.co.ch07.service;

import org.springframework.beans.factory.annotation.Autowired;

import kr.co.ch07.persistence.EmployeeRepo;
import kr.co.ch07.vo.EmployeeVo;

public class EmployeeService {
	@Autowired
	private EmployeeRepo repo;
	
	public void insertEmployee(EmployeeVo ev) {
		repo.save(ev);
	}
	public void selectEmployee() {}
	public void selectEmployees() {}
	public void updateEmployee() {}
	public void deleteEmployee() {}
	
}
