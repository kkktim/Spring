package kr.co.farmstory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.farmstory.dao.CommentDao;
import kr.co.farmstory.vo.CommentVo;

@Service
public class CommentService {
	@Autowired
	private CommentDao dao;
	
	public void insertComment(CommentVo cv) {
		dao.insertComment(cv);
	}
	public CommentVo selelctComment(int no) {
		return dao.selelctComment(no);
	}
	public void selelctComments() {
		
	}
	public void updateComment() {}
	public void deleteComment() {}
	
	//원글에 comment +1
	public void commentPlus(int no) {
		dao.commentPlus(no);
	}
}
