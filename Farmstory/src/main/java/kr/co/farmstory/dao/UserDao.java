package kr.co.farmstory.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.farmstory.vo.TermsVo;
import kr.co.farmstory.vo.UserVo;

@Mapper
@Repository
public interface UserDao {

	public void insertUser(UserVo uv);
	
	public TermsVo selectTerms();
	public UserVo selectUser(UserVo uv);
	public List<UserVo> selectUsers();
	public void updateUser(UserVo uv);
	public void deleteUser(String uid);
	
	//Validation
	public int countUserUid(String uid);
	public int countUserNick(String nick);
	public int countUserEmail(String email);
	public int countUserHp(String hp);
}
