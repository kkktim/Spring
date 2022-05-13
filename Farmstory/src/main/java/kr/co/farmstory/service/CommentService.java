package kr.co.farmstory.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.farmstory.dao.CommentDao;
import kr.co.farmstory.vo.CommentVo;

@Service
public class CommentService {
	@Autowired
	private CommentDao dao;
	
	public int insertComment(CommentVo cv) {
		dao.insertComment(cv);
		return cv.getNo();
	}
	
	public CommentVo selectComment(int no) {
		return dao.selectComment(no);
	}
	
	public int updateComment(CommentVo cv) {
		return dao.updateComment(cv);
	}
	
	public int deleteComment(int no) {
		return dao.deleteComment(no);
	}
	
	//원글에 comment +1
	public void commentPlus(int no) {
		dao.commentPlus(no);
	}
	//원글에 comment -1
	public void commentMinus(int parent) {
		dao.commentMinus(parent);
	}
}
