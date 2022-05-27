package kr.co.kmarket.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kmarket.vo.MemberVo;
import kr.co.kmarket.vo.TermsVo;

@Mapper
@Repository
public interface MemberDao {

	public MemberVo selectMember(MemberVo mv);
	
	public TermsVo selectTerms();
	public void insertMember(MemberVo vo);
	public void insertSeller(MemberVo vo);
	
	public int CountUid(String uid);
	public int CountTel(String tel);
	public int CountFax(String fax);
	public int CountCompany(String company);
	public int CountBizRegNum(String bizRegNum);
	public int CountCommRepNum(String commRepNum);
	public int CountManagerHp(String managerHp);
	public int CountHp(String hp);
	public int CountEmail(String email);
}
