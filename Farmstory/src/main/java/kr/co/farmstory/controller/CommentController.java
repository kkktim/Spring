package kr.co.farmstory.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import kr.co.farmstory.service.CommentService;
import kr.co.farmstory.vo.CommentVo;

@Controller
public class CommentController {

	@Autowired
	private CommentService service;
	
	@ResponseBody
	@GetMapping("/comment/register")
	public String register(HttpServletRequest req, CommentVo cv) {
		
		String regip = req.getRemoteAddr();
		cv.setRegip(regip);
		cv.setParent(cv.getNo());
		
		//댓글 등록
		int no = service.insertComment(cv);
		
		//원글에 댓글 수 +1
		service.commentPlus(cv.getNo());
		
		//댓글 조회
		CommentVo comment = service.selectComment(no);
	
		//json 데이터 생성
		Gson gson = new Gson();
		String jsonData = gson.toJson(comment);
		
		System.out.println("jsonData : "+jsonData);
		return jsonData;
	}
	
	@ResponseBody
	@GetMapping("/comment/delete")
	public int commentDelete(int no, int parent) {
		//댓글 삭제
		int result = service.deleteComment(no);
		System.out.println("result : "+result);
		//원글에 댓글 수 -1
		service.commentMinus(parent);
		
		return result;
	}
}
