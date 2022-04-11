package kr.co.ch07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch07.persistence.MemberRepo;
import kr.co.ch07.vo.MemberVo;

@Service
public class MemberService {
	@Autowired
	private MemberRepo repo;
	
	public void insertMember(MemberVo mv) {
		
		repo.save(mv);
	}
	public MemberVo selectMember(String uid) {
		return repo.findById(uid).get();
	}
	public List<MemberVo> selectMembers() {
		return repo.findAll();
	}
	public void updateMember(MemberVo mv) {
		repo.save(mv);
	}
	public void deleteMember(String uid) {
		repo.deleteById(uid);
	}
	
}
