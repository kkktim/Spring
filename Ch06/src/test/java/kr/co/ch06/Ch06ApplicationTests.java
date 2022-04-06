package kr.co.ch06;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.ch06.persistence.UserDao;
import kr.co.ch06.vo.UserVo;

@SpringBootTest
class Ch06ApplicationTests {

	@Test
	void contextLoads() {
	}
	
	
//	@Autowired
//	private UserDao dao;
//	
//	@Test
//	public void selectTest() {
//		
//		UserVo user = dao.selectUser("kim");
//		System.out.println("uid : "+user.getUid());
//		
//	}

}
















