package kr.co.farmstory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.farmstory.service.BoardService;
import kr.co.farmstory.vo.ArticleVo;

@Controller
public class BoardController {
	@Autowired
	private BoardService service;
	
	@GetMapping("/board/list")
	public String list(Model model, String cate, String type) {
		List<ArticleVo> articles = service.selectArticles();
		model.addAttribute("articles", articles);
		model.addAttribute("cate", cate);
		model.addAttribute("type", type);
		return "/board/list";
	}
	
	@GetMapping("/board/write")
	public String write() {
		return "/board/write";
	}
	
	@GetMapping("/board/view")
	public String view() {
		return "/board/view";
	}
	
	@GetMapping("/board/modify")
	public String modify() {
		return "/board/modify";
	}
	
}