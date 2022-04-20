package kr.co.sboard1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.sboard1.vo.ArticleVo;
import kr.co.sboard1.vo.FileVo;

@Mapper
@Repository
public interface ArticleDao {

	//useGeneratedKeys="true" keyProperty="no" - xml 에 선언해줘야 리턴값 생김
	public int insertArticle(ArticleVo av);
	public void insertFile(FileVo fv);
	public ArticleVo selectArticle(int no);
	public List<ArticleVo> selectArticles(int start);
	public int selectCountTotal();
	public void updateArticle();
	public void deleteArticle();
	
}
