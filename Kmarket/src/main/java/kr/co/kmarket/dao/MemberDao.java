package kr.co.kmarket.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kmarket.vo.MemberVo;

@Mapper
@Repository
public interface MemberDao {

	public MemberVo selectMember(MemberVo mv);
}
