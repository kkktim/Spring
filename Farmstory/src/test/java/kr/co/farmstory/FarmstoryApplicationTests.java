package kr.co.farmstory;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.farmstory.service.BoardService;
import kr.co.farmstory.vo.ArticleVo;

@SpringBootTest
class FarmstoryApplicationTests {

	@Autowired
	private BoardService service;
	
	void contextLoads() {
	}

	@Test
	public void selectArticles() {
		List<ArticleVo> articles = service.selectArticles();
		ArticleVo article = articles.get(0);
		System.out.println("nick : "+article.getUv().getNick());
	}
}
