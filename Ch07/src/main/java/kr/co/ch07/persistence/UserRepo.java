package kr.co.ch07.persistence;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.co.ch07.vo.UserVo;
//@repository 없어도 됨
//entity = UserVo , ID = Id 지정한 객체 타입 string
public interface UserRepo extends JpaRepository<UserVo, String> {
	
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/ - Query Methods
	//JPA 쿼리 메서드 작성
	public List<UserVo> findUserVoByOrderByName();   //find + entity + query문 + column명
	public List<UserVo> findUserVoByOrderByAgeAsc();
	public List<UserVo> findUserVoByOrderByAgeDesc();
	
	// 사용자 정의 쿼리 메서드 작성
	// @Query 어노테이션 작성
	// JPQL 작성
	@Query("SELECT a FROM UserVo as a WHERE age < 30")
	public List<UserVo> selectUserUnderAge30();
}
