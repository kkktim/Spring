package kr.co.ch09.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch09.dao.UserDao;
import kr.co.ch09.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao dao;
	
	public void insertUser(UserVo uv) {
		dao.insertUser(uv);
	}
	public UserVo selectUser(String uid) {
		return dao.selectUser(uid);
	}
	public List<UserVo> selectUsers() {
		return dao.selectUsers();
	}
	public void updateUser(UserVo uv) {
		dao.updateUser(uv);
	}
	public void deleteUser(String uid) {
		dao.deleteUser(uid);
	}
	
}
