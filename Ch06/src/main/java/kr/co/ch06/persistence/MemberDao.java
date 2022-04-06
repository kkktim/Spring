package kr.co.ch06.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.ch06.vo.MemberVo;

@Repository
public interface MemberDao {

	//CRUD 메서드
	public void insertMember(MemberVo mv);
	public MemberVo selectMember(String uid);
	public List<MemberVo> selectMembers();
	public void updateMember(MemberVo mv);
	public void deleteMember(String uid);
}
