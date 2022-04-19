package kr.co.sboard1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.Repository;

import kr.co.sboard1.dao.ArticleDao;
import kr.co.sboard1.persistence.ArticleRepo;
import kr.co.sboard1.persistence.UserRepo;
import kr.co.sboard1.vo.ArticleVo;

@SpringBootTest
class Sboard1ApplicationTests {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private ArticleRepo articleRepo;
	
	void contextLoads() {
		System.out.println("Hello JUnit!");
	}
	
	public void testCountUser() {
		int result = userRepo.countUserVoByUid("tes123t");
		System.out.println("result : "+result);
	}
	
	public void testArticleInsert() {
		ArticleVo av = new ArticleVo();
		av.setTitle("테스트 입니다.");
		av.setContent("테스트 내용 입니다.");
		av.setUid("test");
		av.setRegip("128.0.0.1");
		
		//JPA
		articleRepo.save(av);
		
//		int no = article.getNo();
//		System.out.println("글번호 : "+no);
		
		
		//Mybatis
//		articleDao.insertArticle(av);
		//방금 Insert한 글번호 리턴 (JPA, MyBatis)
		int no = av.getNo();
		
		System.out.println("글번호 : "+no);
	}
	@Test
	public void testArticleSelect() {
		ArticleVo av = new ArticleVo();
		
		ArticleVo article = articleDao.selectArticle(3);
		String oName = article.getFv().getOName();
		System.out.println("파일 이름 : "+oName);
	}
	
}
