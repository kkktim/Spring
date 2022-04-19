package kr.co.sboard1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sboard1.dao.UserDao;
import kr.co.sboard1.persistence.UserRepo;
import kr.co.sboard1.vo.TermsVo;
import kr.co.sboard1.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao dao;
	
	@Autowired
	private UserRepo repo;
	
	public void insertUser(UserVo uv) {
		dao.insertUser(uv);
	}
	
	public TermsVo selectTerms() {
		return dao.selectTerms();
	}
	//validation
	public int CountUserUid(String uid) {
		return repo.countUserVoByUid(uid);
	}
	public int CountUserNick(String nick) {
		return repo.countUserVoByNick(nick);
	}
	
	public UserVo selectUser(UserVo uv) {
		return dao.selectUser(uv);
	}
	public void selectUsers() {}
	public void updateUser() {}
	public void deleteUser() {}
	
}
