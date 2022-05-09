package kr.co.sboard1.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.co.sboard1.entity.ArticleEntity;
import kr.co.sboard1.service.ArticleService;
import kr.co.sboard1.vo.ArticleVo;
import kr.co.sboard1.vo.FileVo;
import kr.co.sboard1.vo.UserVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	public String list(@ModelAttribute("sessUser") UserVo sessUser, 
						Model model, String pg,
						String searchKeyword) {

		log.info("list - 1");
		
		if(sessUser == null) {
			log.info("list - 2");
			return "redirect:/user/login?success=102";
		}else {
			log.info("list - 3");
			
			int currentPage = service.getCurrentPage(pg);
			int total = service.selectCountTotal();
			int lastPageNum = service.getLastPageNum(total);
			int start = service.getLimitStart(currentPage);
			int pageStartNum = service.getPageStartNum(total, start);
			int groups[] = service.getPageGroup(currentPage, lastPageNum);
			
			if(searchKeyword == null) {
				model.addAttribute("articles", service.selectArticles(start));
			}else {
				model.addAttribute("articles", service.selectSearchKeyword(searchKeyword));
			}
			
//			model.addAttribute("articles", articles);
//			model.addAttribute("searchLists", searchLists);
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
	public String modify(@ModelAttribute("sessUser") UserVo sessUser, int no, Model model) {
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}
		ArticleVo article = service.selectArticle(no);
		model.addAttribute("article", article);
		return "/article/modify";
		
	}
	@PostMapping("/article/modify")
	public String modify(@ModelAttribute("sessUser") UserVo sessUser, ArticleEntity ae, HttpServletRequest req) {
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}
		String regip = req.getRemoteAddr();
		ae.setRegip(regip);
		service.updateArticle(ae);
		return "redirect:/article/list";
		
	}
	
	//view
	@GetMapping("/article/view")
	public String view(@ModelAttribute("sessUser") UserVo sessUser, int no, Model model) {
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}else {
			ArticleVo article = service.selectArticle(no);
//			String oName = article.getFv().getOName();
//			System.out.println("파일 이름 : "+oName);
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
		
		log.info("write - 1");
		String regip = req.getRemoteAddr();
		
		log.info("write - 2");
		av.setRegip(regip);
		
		log.info("write - 3");
		//파일 첨부 여부 확인 - isEmpty = boolean , defalut 값 true
		if(av.getFname().isEmpty()) {
			// 파일 첨부 안했을 때
			log.info("write - 4");
			av.setFile(0);
			service.insertArticle(av);
		}else {
			//파일 첨부 했을 때 - 글 등록
			log.info("write - 5");
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
	
	// delete
	@GetMapping("/article/delete")
	public String delete(int no) {
		service.deleteArticle(no);
		return "redirect:/article/list";
	}
	
	
	//파일 다운로드
	@GetMapping("/article/filedownload")
	public void filedownload(int fid, HttpServletResponse resp) {
		//파일 다운로드 카운트 +1
		
		//파일 다운로드 정보 조회
		FileVo fv = service.selectFile(fid);
		
		//파일 다운로드
		service.fileDownload(resp, fv);
	}
	
	//댓글
	@ResponseBody
	@GetMapping("/article/comment/{no}")
	public List<ArticleVo> comment(@PathVariable("no") int no) {
		List<ArticleVo> comments = service.selectComments(no);
		//list 라서 json 안만들어도됨
		return comments;
	}
	@ResponseBody
	@PostMapping("/article/comment")
	public Map<String, Integer> comment(ArticleVo av, HttpServletRequest req) {
		String regip = req.getRemoteAddr();
		av.setRegip(regip);
		int result = service.insertComment(av);
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		
		return map;
	}
	
}
