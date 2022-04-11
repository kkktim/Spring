package kr.co.ch07.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.ch07.vo.EmployeeVo;

public interface EmployeeRepo extends JpaRepository<EmployeeVo, String> {
	
}
