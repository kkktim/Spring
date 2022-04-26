package kr.co.farmstory.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.farmstory.vo.ArticleVo;

@Mapper
@Repository
public interface BoardDao {

	public void insertArticle(ArticleVo av);
	public ArticleVo selectArticle(int no);
	public List<ArticleVo> selectArticles();
	public void updateArticle(ArticleVo av);
	public void deleteArticle(int no);
	
}
