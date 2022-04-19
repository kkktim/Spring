package kr.co.sboard1.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.sboard1.vo.FileVo;

public interface FileRepo extends JpaRepository<FileVo, Integer>{
	
}
