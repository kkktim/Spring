package kr.co.ch06.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.ch06.vo.StudentVo;

@Mapper
@Repository
public interface StudentDao {

	public void insertStudent(StudentVo sv);
	public StudentVo selectStudent(String sid);
	public List<StudentVo> selectStudents();
	public int updateStudent(StudentVo sv);
	public void deleteStudent(String sid);
}
