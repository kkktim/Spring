package kr.co.farmstory.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.farmstory.dao.UserDao;
import kr.co.farmstory.vo.TermsVo;
import kr.co.farmstory.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao dao;
	
	public void insertUser(UserVo uv) {
		dao.insertUser(uv);
	}
	
	public TermsVo selectTerms() {
		return dao.selectTerms();
	}
	public UserVo selectUser(UserVo uv) {
		return dao.selectUser(uv);
	}
	public void selectUsers() {}
	public void updateUser(UserVo uv) {}
	public void deleteUser(String uid) {}
	
	//Validation - 중복체크
	public int countUserUid(String uid) {
		return dao.countUserUid(uid);
	}
	public int countUserNick(String nick) {
		return dao.countUserNick(nick);
	}
	public int countUserEmail(String email) {
		return dao.countUserEmail(email);
	}
	public int countUserHp(String hp) {
		return dao.countUserHp(hp);
	}
}
