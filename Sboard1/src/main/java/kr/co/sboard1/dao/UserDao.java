package kr.co.sboard1.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.sboard1.vo.TermsVo;
import kr.co.sboard1.vo.UserVo;

@Mapper
@Repository
public interface UserDao {

	public void insertUser(UserVo uv);
	public TermsVo selectTerms();
	public UserVo selectUser(UserVo uv);
	public void selectUsers();
	public void updateUser();
	public void deleteUser();
	
}
