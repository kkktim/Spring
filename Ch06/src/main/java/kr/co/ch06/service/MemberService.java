package kr.co.ch06.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kr.co.ch06.persistence.MemberDao;
import kr.co.ch06.vo.MemberVo;

@Service
public class MemberService {
	@Autowired
	private MemberDao dao;
	
	public void insertMember(MemberVo mv) {
		dao.insertMember(mv);
	}
	
	public MemberVo selectMember(String uid) {
		MemberVo member = dao.selectMember(uid);
		return member;
	}
	public List<MemberVo> selectMembers() {
		List<MemberVo> members = dao.selectMembers();
		return members;
	}
	public void updateMember(MemberVo mv) {
		dao.updateMember(mv);
	}
	public void deleteMember(String uid) {
		dao.deleteMember(uid);
	}
}
