package kr.co.sboard1.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.sboard1.entity.FileEntity;

public interface FileRepo extends JpaRepository<FileEntity, Integer>{
	
}
