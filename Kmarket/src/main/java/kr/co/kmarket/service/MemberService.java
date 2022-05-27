package kr.co.kmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket.dao.MemberDao;
import kr.co.kmarket.vo.MemberVo;
import kr.co.kmarket.vo.TermsVo;

@Service
public class MemberService {

	@Autowired
	private MemberDao dao;
	
	public MemberVo selectMember(MemberVo mv) {
		return dao.selectMember(mv);
	}

	public TermsVo selectTerms() {
		return dao.selectTerms();
	}
	
	public void insertMember(MemberVo vo) {
		dao.insertMember(vo);
	}
	
	public void insertSeller(MemberVo vo) {
		dao.insertSeller(vo);
	}
	
	
	public int CountSellerUid(String uid) {
		return dao.CountUid(uid);
	}
	public int CountSellerTel(String tel) {
		return dao.CountTel(tel);
	}
	public int CountSellerFax(String fax) {
		return dao.CountFax(fax);
	}
	public int CountSellerCompany(String company) {
		return dao.CountCompany(company);
	}
	public int CountSellerBizRegNum(String bizRegNum) {
		return dao.CountBizRegNum(bizRegNum);
	}
	public int CountSellerCommRepNum(String commRepNum) {
		return dao.CountCommRepNum(commRepNum);
	}
	public int CountSellerManagerHp(String managerHp) {
		return dao.CountManagerHp(managerHp);
	}
	public int CountHp(String hp) {
		return dao.CountHp(hp);
	}
	public int CountSellerEmail(String email) {
		return dao.CountEmail(email);
	}
}
