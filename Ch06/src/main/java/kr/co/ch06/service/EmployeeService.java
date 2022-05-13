package kr.co.ch06.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch06.persistence.EmployeeDao;
import kr.co.ch06.vo.EmployeeVo;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDao dao;
	
	public void insertEmployee(EmployeeVo ev) {
		dao.insertEmployee(ev);
	}
	public EmployeeVo selectEmployee(String uid) {
		return dao.selectEmployee(uid);
	}
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
