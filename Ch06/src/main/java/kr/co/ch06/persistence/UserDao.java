package kr.co.ch06.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.ch06.vo.UserVo;

@Repository
public interface UserDao {
	//mapping 하기 때문에, interface method 선언만
	//user.xml 에 매핑
	
	public void insertUser(UserVo vo);
	public UserVo selectUser(String uid);   //변수이름   여기서 xml로 넘김
	public List<UserVo> selectUsers();
	public void updateUser(UserVo vo);
	public void deleteUser(String uid);
}
