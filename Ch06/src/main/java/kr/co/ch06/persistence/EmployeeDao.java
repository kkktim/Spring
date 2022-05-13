package kr.co.ch06.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.ch06.vo.EmployeeVo;

@Mapper
@Repository
public interface EmployeeDao {

	public void insertEmployee(EmployeeVo ev);
	public EmployeeVo selectEmployee(String uid);
	public List<EmployeeVo> selectEmployees();
	public void updateEmployee(EmployeeVo ev);
	public void deleteEmployee(String uid);
	
}
