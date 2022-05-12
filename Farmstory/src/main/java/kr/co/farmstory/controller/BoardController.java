package kr.co.farmstory.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import kr.co.farmstory.service.BoardService;
import kr.co.farmstory.vo.ArticleVo;
import kr.co.farmstory.vo.CommentVo;
import kr.co.farmstory.vo.FileVo;
import kr.co.farmstory.vo.UserVo;

@SessionAttributes("sessUser")
@Controller
public class BoardController {
	
	@ModelAttribute("sessUser")
	public UserVo setUserVo() {
		return null;
	}
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/board/list")
	public String list(@ModelAttribute("sessUser") UserVo sessUser, Model model, String cate, String type, String pg) {
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}
		
		//페이지 처리
		int currentPage = service.getCurrentPage(pg);
		int total = service.selectCountTotal(type);
		int lastPageNum = service.getLastPageNum(total);
		int start = service.getLimitStart(currentPage);
		int pageStartNum = service.getPageStartNum(total, start);
		int groups[] = service.getPageGroup(currentPage, lastPageNum);
		
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageStartNum", pageStartNum);
		model.addAttribute("groups", groups);
		
		List<ArticleVo> articles = service.selectArticles(type, start);
		
		model.addAttribute("articles", articles);
		model.addAttribute("cate", cate);
		model.addAttribute("type", type);
		model.addAttribute("sessUser", sessUser);
		
		return "/board/list";
	}
	//Write
	@GetMapping("/board/write")
	public String write(@ModelAttribute("sessUser") UserVo sessUser, Model model, String cate, String type) {
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}
		
		model.addAttribute("type", type);
		model.addAttribute("cate", cate);
		
		return "/board/write";
	}
	@PostMapping("/board/write")
	public String write(@ModelAttribute("sessUser") UserVo sessUser, ArticleVo av, HttpServletRequest req, String cate, String type) {
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}
		
		String regip = req.getRemoteAddr();
		av.setRegip(regip);
		av.setType(type);
		
		if(av.getFname().isEmpty()) {
			//파일 첨부 X
			av.setFile(0);
			service.insertArticle(av);
			
		}else {
			//파일 첨부 O
			av.setFile(1);
			int no = service.insertArticle(av);
//			System.out.println("no : "+no);
			//파일 글 등록
			FileVo fv = service.fileUpload(av.getFname());
			fv.setParent(no);
			System.out.println("fv : "+fv.getNName());
			service.insertFile(fv);
			
		}
		
		
		return "redirect:/board/list?cate="+cate+"&type="+type;
	}
	//file download
	@GetMapping("/board/filedownload")
	public void fileDownload(int fid, HttpServletResponse resp) {
			
		//파일 정보 조회
		FileVo fv = service.selectFile(fid);
		//다운로드
			
		//다운로드
		service.fileDownload(resp, fv);
			
		//다운로드 횟수 +1
//		if(result > 0) {
//			service.downCountPlus(fid);
//		}
		service.downCountPlus(fid);
	}
		
	//View
	@GetMapping("/board/view")
	public String view(@ModelAttribute("sessUser") UserVo sessUser, String cate, String type, int no, Model model) {
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}
		//글 가져오기
		ArticleVo article = service.selectArticle(no);
		List<CommentVo> comments = null;
		if(article.getComment() > 0) {
			comments = service.selectComments(no);
		}
		//댓글 가져오기
		
		model.addAttribute("cate", cate);
		model.addAttribute("type", type);
		model.addAttribute("sessUser", sessUser);
		model.addAttribute("article", article);
		model.addAttribute("comments", comments);
		
		return "/board/view";
	}
	
	//Modify
	@GetMapping("/board/modify")
	public String modify(@ModelAttribute("sessUser") UserVo sessUser, String cate, String type, int no, Model model) {
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}
		ArticleVo article = service.selectArticle(no);
//		System.out.println("get 방식 fid : "+article.getFv().getFid());
		model.addAttribute("article", article);
		model.addAttribute("cate", cate);
		model.addAttribute("type", type);
		return "/board/modify";
	}
	@PostMapping("/board/modify")
	public String modify(@ModelAttribute("sessUser") UserVo sessUser, ArticleVo av, String cate, String type, int fid) {
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}
		System.out.println("no : "+av.getNo());
		System.out.println("no : "+fid);
		if(av.getFname().isEmpty()) {
			//첨부파일 수정 x
			service.updateArticle(av);
		}else {
			//첨부파일 수정 o
			//파일 업로드
			FileVo fv = service.fileUpload(av.getFname());
			
			//파일 테이블 INSERT
			fv.setParent(av.getNo());
			service.insertFile(fv);
			
			//전 파일 테이블 삭제
			FileVo attfile = service.selectFile(fid);
			service.deleteFile(attfile.getFid());
			
			//전 파일 삭제
			service.deleteAttachedFile(attfile);
		}
		
		
		return "redirect:/board/view?cate="+cate+"&type="+type+"&no="+av.getNo();
	}
	
	//DELETE
	@GetMapping("/board/delete")
	public String delete(@ModelAttribute("sessUser") UserVo sessUser, String cate, String type, int no) {
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}
		//글 상태 조회
		ArticleVo article = service.selectArticle(no);
		//글 삭제
		service.deleteArticle(no);
		
		if(article.getFile() > 0) {
			//파일 테이블 글 삭제
			service.deleteFile(article.getFv().getFid());
			//첨부파일 삭제
			service.deleteAttachedFile(article.getFv());
		}
		
		
		return "redirect:/board/list?cate="+cate+"&type="+type;
	}
	
}