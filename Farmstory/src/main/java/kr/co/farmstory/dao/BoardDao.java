package kr.co.farmstory.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.farmstory.vo.ArticleVo;
import kr.co.farmstory.vo.CommentVo;
import kr.co.farmstory.vo.FileVo;

@Mapper
@Repository
public interface BoardDao {
	//INSERT
	public void insertFile(FileVo fv);
	public int insertArticle(ArticleVo av);
	
	//SELECT
	public int selectCountTotal(String type);
	public FileVo selectFile(int fid);
	public ArticleVo selectArticle(int no);
	public List<ArticleVo> selectArticles(String type, int start);
	public List<CommentVo> selectComments(int no);
	
	//UPDATE
	public void updateArticle(ArticleVo av);
	public void updateArticleHit(int no);
	
	//DELETE
	public void deleteFile(int fid);
	public void deleteArticle(int no);
	
	//파일 다운로드 +1
	public void downCountPlus(int fid);
}
