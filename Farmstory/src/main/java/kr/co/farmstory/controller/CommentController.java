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
		//ano = 본문 글 번호
		int ano = cv.getNo(); 
		String regip = req.getRemoteAddr();
		cv.setRegip(regip);
		cv.setParent(ano);
		
		//댓글 등록 no = 댓글 번호
		int no = service.insertComment(cv);

		//원글에 댓글 수 +1
		service.commentPlus(ano);
		
		//댓글 조회
		CommentVo comment = service.selectComment(no);
	
		//json 데이터 생성
		Gson gson = new Gson();
		String jsonData = gson.toJson(comment);
		
		return jsonData;
	}
	
	@ResponseBody
	@GetMapping("/comment/modify")
	public String commentModify(int no, String content) {
		CommentVo cv = new CommentVo();
		cv.setNo(no);
		cv.setContent(content);
		
		int result = service.updateComment(cv);
		
		Gson gson = new Gson();
		String jsonData = gson.toJson(result);
		
		return jsonData;
	}
	
	@ResponseBody
	@GetMapping("/comment/delete")
	public String commentDelete(int no, int parent) {
		//댓글 삭제
		int result = service.deleteComment(no);

		//원글에 댓글 수 -1
		service.commentMinus(parent);
		
		Gson gson = new Gson();
		String jsonData = gson.toJson(result);
		
		return jsonData;
	}
}
