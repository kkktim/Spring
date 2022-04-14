package kr.co.ch09.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch09.dao.MemberDao;
import kr.co.ch09.vo.MemberVo;

@Service
public class MemberService {
	@Autowired
	private MemberDao dao;
	
	public void insertMember(MemberVo mv) {
		dao.insertMember(mv);
	}
	public void selectMember() {}
	public List<MemberVo> selectMembers() {
		return dao.selectMembers();
	}
	public void updateMember(MemberVo mv) {
		dao.updateMember(mv);
	}
	public void deleteMember(String uid) {
		dao.deleteMember(uid);
	}
	
}
