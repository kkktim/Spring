package kr.co.ch08.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.ch08.persistence.UserRepo;
import kr.co.ch08.vo.UserVo;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepo repo;
	
	public void insertUser(UserVo uv) {
		//암호화 클래스(강력함) - 비밀번호 암호화 처리
		BCryptPasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
		uv.setPass(passwordEncorder.encode(uv.getPass()));
		
		repo.save(uv);
	}
	public UserVo selectUser(String uid) {
		return repo.findById(uid).get();
	}
	public List<UserVo> selectUsers() {
		return repo.findAll();
	}
	public void updateUser(UserVo uv) {
		repo.save(uv);
	}
	public void deleteUser(String uid) {
		repo.deleteById(uid);
	}
	
	//인증관리 filter 에서 서비스로 바로 요청 (username = 클라이언트가 입력한 아이디)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserVo userVo = repo.findById(username).get();
		
		if(userVo == null) {
			System.out.println("user 없음!");
			//예외 던짐
			throw new UsernameNotFoundException(username);
		}
		
		// User = org.springframework.security.core.userdetails
		return User.builder().
				username(userVo.getUid()).
				password(userVo.getPass()).
				roles("USER").
				build();
	}

}
