package kr.co.ch09.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.ch09.vo.EmployeeVo;

@Repository
@Mapper
public interface EmployeeDao {

	public void insertEmployee(EmployeeVo ev);
	public void selectEmployee();
	public List<EmployeeVo> selectEmployees();
	public void updateEmployee(EmployeeVo ev);
	public void deleteEmployee(String uid);
	
}
