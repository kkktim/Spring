package kr.co.sboard1.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.co.sboard1.service.ArticleService;
import kr.co.sboard1.vo.ArticleVo;
import kr.co.sboard1.vo.FileVo;
import kr.co.sboard1.vo.UserVo;

@SessionAttributes("sessUser")
@Controller
public class ArticleController {
	
	@ModelAttribute("sessUser")
	public UserVo setUserVo() {
		return null;
	}
	
	@Autowired
	private ArticleService service;
	
	//list
	@GetMapping("/article/list")
	public String list(@ModelAttribute("sessUser") UserVo sessUser, Model model, String pg) {

		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}else {
			int currentPage = service.getCurrentPage(pg);
			int total = service.selectCountTotal();
			int lastPageNum = service.getLastPageNum(total);
			int start = service.getLimitStart(currentPage);
			int pageStartNum = service.getPageStartNum(total, start);
			int groups[] = service.getPageGroup(currentPage, lastPageNum);
			
			List<ArticleVo> articles = service.selectArticles(start);
			model.addAttribute("articles", articles);
			model.addAttribute("lastPageNum", lastPageNum);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("pageStartNum", pageStartNum);
			model.addAttribute("groups", groups);
			return "/article/list";
		}
		
	}
	//modify
	@GetMapping("/article/modify")
	public String modify(@ModelAttribute("sessUser") UserVo sessUser) {
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}else {
			return "/article/modify";
		}
		
	}
	//view
	@GetMapping("/article/view")
	public String view(@ModelAttribute("sessUser") UserVo sessUser, int no, Model model) {
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}else {
			System.out.println("no : "+no);
			ArticleVo article = service.selectArticle(no);
			String oName = article.getFv().getOName();
			System.out.println("파일 이름 : "+oName);
			model.addAttribute("article", article);
			return "/article/view";
		}
		
	}
	//write
	@GetMapping("/article/write")
	public String write(@ModelAttribute("sessUser") UserVo sessUser) {
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}else {
			return "/article/write";
		}
	}
	@PostMapping("/article/write")
	public String write(@ModelAttribute("sessUser") UserVo sessUser, ArticleVo av, HttpServletRequest req) {
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}
		String regip = req.getRemoteAddr();
		av.setRegip(regip);
		//파일 첨부 여부 확인 - isEmpty = boolean , defalut 값 true
		if(av.getFname().isEmpty()) {
			// 파일 첨부 안했을 때
			av.setFile(0);
			service.insertArticle(av);
		}else {
			//파일 첨부 했을 때 - 글 등록
			av.setFile(1);
			int no = service.insertArticle(av);
			//파일 업로드
			FileVo fv = service.fileUpload(av.getFname());
			//파일 테이블 Insert
			fv.setParent(no);   //글 등록 후 no리턴 받아서 파일테이블 parent 입력
			service.insertFile(fv);
		}
		
		
		
		return "redirect:/article/list";
	}
}
