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
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.co.farmstory.service.BoardService;
import kr.co.farmstory.vo.ArticleVo;
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
	public String list(@ModelAttribute("sessUser") UserVo sessUser, Model model, String cate, String type) {
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}
		List<ArticleVo> articles = service.selectArticles(type);
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
			System.out.println("no : "+no);
			//파일 글 등록
			FileVo fv = service.fileUpload(av.getFname());
			fv.setParent(no);
			System.out.println("fv : "+fv.getNName());
			service.insertFile(fv);
			
		}
		
		
		return "redirect:/board/list?cate="+cate+"&type="+type;
	}
	//View
	@GetMapping("/board/view")
	public String view(@ModelAttribute("sessUser") UserVo sessUser, String cate, String type, int no, Model model) {
		if(sessUser == null) {
			return "redirect:/user/login?success=102";
		}
		ArticleVo article = service.selectArticle(no);
		
		model.addAttribute("cate", cate);
		model.addAttribute("type", type);
		model.addAttribute("sessUser", sessUser);
		model.addAttribute("article", article);
		
		int fid = article.getFv().getFid();
		System.out.println("fid : "+fid);
		return "/board/view";
	}
	//file download
	@GetMapping("/board/filedownload")
	public void fileDownload(int fid, HttpServletResponse resp) {
		
		//파일 정보 조회
		FileVo fv = service.selectFile(fid);
		System.out.println("oName : "+fv.getOName());
		//다운로드
//		int result = service.fileDownload(resp, fv);
//		System.out.println("result : "+result);
		
		//다운로드
		service.fileDownload(resp, fv);
		
		//다운로드 횟수 +1
//		if(result > 0) {
//			service.downCountPlus(fid);
//		}
		service.downCountPlus(fid);
	}
	
	@GetMapping("/board/modify")
	public String modify() {
		return "/board/modify";
	}
	
}