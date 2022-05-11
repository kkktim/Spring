package kr.co.farmstory.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.farmstory.vo.CommentVo;

@Repository
@Mapper
public interface CommentDao {

	public void insertComment(CommentVo cv);
	public CommentVo selectComments(int no);
	public void selelctComments();
	
	public void updateComment();
	public void deleteComment();
	
	public void commentPlus(int no);
	
}
