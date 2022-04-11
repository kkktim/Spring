package kr.co.ch07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch07.persistence.StudentRepo;
import kr.co.ch07.vo.StudentVo;

@Service
public class StudentService {

	@Autowired
	private StudentRepo repo;
	
	public void insertStudent(StudentVo sv) {
		repo.save(sv);
	}
	public StudentVo selectStudent(String sid) {
		return repo.findById(sid).get();
	}
	public List<StudentVo> selectStudents() {
		return repo.findAll();
	}
	public StudentVo updateStudent(StudentVo sv) {
		return repo.save(sv);
	}
	public void deleteStudent(String sid) {
		repo.deleteById(sid);
	}
	
}
