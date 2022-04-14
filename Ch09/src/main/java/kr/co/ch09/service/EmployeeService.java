package kr.co.ch09.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch09.dao.EmployeeDao;
import kr.co.ch09.vo.EmployeeVo;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeDao dao;
	
	public void insertEmployee(EmployeeVo ev) {
		dao.insertEmployee(ev);
	}
	public void selectEmployee() {}
	public List<EmployeeVo> selectEmployees() {
		return dao.selectEmployees();
	}
	public void updateEmployee(EmployeeVo ev) {
		dao.updateEmployee(ev);
	}
	public void deleteEmployee(String uid) {
		dao.deleteEmployee(uid);
	}
	
}
