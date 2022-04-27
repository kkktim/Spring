package kr.co.farmstory;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.farmstory.dao.BoardDao;
import kr.co.farmstory.service.BoardService;
import kr.co.farmstory.vo.ArticleVo;
import kr.co.farmstory.vo.FileVo;

@SpringBootTest
class FarmstoryApplicationTests {

	@Autowired
	private BoardService service;
	
	@Autowired
	private BoardDao dao;
	
	void contextLoads() {
	}

	
	public void selectArticles() {
		String type = "story";
		List<ArticleVo> articles = service.selectArticles(type);
		ArticleVo article = articles.get(0);
		System.out.println("nick : "+article.getUser().getNick());
	}
	
	public void insertAttached() {
		ArticleVo av = new ArticleVo();
		av.setType("chef");
		av.setTitle("파일 첨부 테스트");
		av.setContent("파일 첨부 테스트");
		av.setFile(1);
		av.setUid("spring");
		av.setRegip("123.0.0.23.04");
		av.setRdate("2022-04-27 11:52:55");
		
		dao.insertArticle(av);
		int no = av.getNo();
		System.out.println("no : "+no);
	}
	
	@Test
	public void selectFileResultMap() {
		int fid = 46;
		FileVo fv = dao.selectFile(fid);
		System.out.println("파일 이름 : "+fv.getOName());
		System.out.println("fid : "+fv.getFid());
	}
}
