package kr.co.farmstory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.farmstory.dao.BoardDao;
import kr.co.farmstory.vo.ArticleVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao dao;
	
	public void insertArticle(ArticleVo av) {}
//	public ArticleVo selectArticle(int no) {}
	public List<ArticleVo> selectArticles() {
		return dao.selectArticles();
	}
	public void updateArticle(ArticleVo av) {}
	public void deleteArticle(int no) {}
	
}
