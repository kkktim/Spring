package kr.co.ch07.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.ch07.vo.StudentVo;

public interface StudentRepo extends JpaRepository<StudentVo, String> {

}
