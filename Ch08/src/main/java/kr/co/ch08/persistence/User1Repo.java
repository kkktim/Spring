package kr.co.ch08.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.ch08.vo.User1Vo;

@Repository
public interface User1Repo extends JpaRepository<User1Vo, String> {

	
}
