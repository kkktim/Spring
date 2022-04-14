package kr.co.ch09.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.ch09.vo.MemberVo;

//mybatis 설정
@Mapper
@Repository
public interface MemberDao {

	public void insertMember(MemberVo mv);
	public void selectMember();
	public List<MemberVo> selectMembers();
	public void updateMember(MemberVo mv);
	public void deleteMember(String uid);
	
}
