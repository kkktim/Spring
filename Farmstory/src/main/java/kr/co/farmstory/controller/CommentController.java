package kr.co.farmstory.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.farmstory.service.CommentService;
import kr.co.farmstory.vo.CommentVo;

@Controller
public class CommentController {
	@Autowired
	private CommentService service;
	
	@ResponseBody
	@PostMapping("/board/comment")
	public String register(HttpServletRequest req, CommentVo cv, int no) throws JsonProcessingException {
		String regip = req.getRemoteAddr();
		
		cv.setRegip(regip);
		cv.setParent(no);
		//댓글 등록
		service.insertComment(cv);
		//원글에 댓글 +1
		service.commentPlus(no);
		//댓글 조회
		CommentVo comment = service.selectComments(no);
		String com = comment.getContent();
		System.out.println("com : "+com);
		//json 데이터 생성
//		Map<String, String> map = new HashMap<>();
//		map.put("comment", comment);
	
		// jsonData 안만들어진거같음 - 확인해보자
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(comment);
		
		return jsonData;
	}
}
