package kr.co.ch09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ch09.service.EmployeeService;
import kr.co.ch09.vo.EmployeeVo;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	//목록
	@GetMapping("/employee")
	public List<EmployeeVo> list() {
		List<EmployeeVo> employees = service.selectEmployees();
		return employees;
	}
	//등록
	@PostMapping("/employee")
	public List<EmployeeVo> register(EmployeeVo ev) {
		System.out.println("ev uid : "+ev.getUid());
		service.insertEmployee(ev);
		return service.selectEmployees(); 
	}
	//수정
	@PutMapping("/employee/{uid}")
	public List<EmployeeVo> modify(@PathVariable("uid") String uid, EmployeeVo ev) {
		ev.setUid(uid);
		service.updateEmployee(ev);
		return service.selectEmployees();
	}
	//삭제
	@DeleteMapping("/employee/{uid}")
	public List<EmployeeVo> delete(@PathVariable("uid") String uid) {
		service.deleteEmployee(uid);
		return service.selectEmployees();
	}
}
