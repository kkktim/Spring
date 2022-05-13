package kr.co.ch06.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch06.persistence.StudentDao;
import kr.co.ch06.vo.StudentVo;

@Service
public class StudentService {
	
	@Autowired
	private StudentDao dao;
	
	public void insertStudent(StudentVo sv) {
		dao.insertStudent(sv);
	}
	public StudentVo selectStudent(String sid) {
		return dao.selectStudent(sid);
	}
	public List<StudentVo> selectStudents() {
		return dao.selectStudents();
	}
	public int updateStudent(StudentVo sv) {
		return dao.updateStudent(sv);
	}
	public void deleteStudent(String sid) {
		dao.deleteStudent(sid);
	}
}
