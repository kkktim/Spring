package kr.co.farmstory.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.farmstory.vo.CommentVo;

@Repository
@Mapper
public interface CommentDao {

	public int insertComment(CommentVo cv);
	
	public CommentVo selectComment(int no);
	
	public int updateComment(CommentVo cv);
	
	public int deleteComment(int no);
	
	public void commentPlus(int no);
	public void commentMinus(int parent);
	
}
