package kr.co.ch09.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.ch09.vo.UserVo;

@Mapper
@Repository
public interface UserDao {

	public void insertUser(UserVo uv);
	public UserVo selectUser(String uid);
	public List<UserVo> selectUsers();
	public void updateUser(UserVo uv);
	public void deleteUser(String uid);
	
}
