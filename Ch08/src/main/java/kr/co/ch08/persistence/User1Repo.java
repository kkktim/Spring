package kr.co.ch08.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.ch08.vo.UserVo;

@Repository
public interface UserRepo extends JpaRepository<UserVo, String> {

	
}
