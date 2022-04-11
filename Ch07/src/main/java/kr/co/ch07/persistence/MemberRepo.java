package kr.co.ch07.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.ch07.vo.MemberVo;

public interface MemberRepo extends JpaRepository<MemberVo, String> {
	
}
