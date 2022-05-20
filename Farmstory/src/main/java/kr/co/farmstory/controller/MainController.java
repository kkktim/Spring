package kr.co.farmstory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.co.farmstory.service.MainService;
import kr.co.farmstory.vo.ArticleVo;
import kr.co.farmstory.vo.UserVo;

@SessionAttributes("sessUser")
@Controller
public class MainController {
	@Autowired
	private MainService service;
	
	//세션 초기화 코드
	@ModelAttribute("sessUser")
	public UserVo setUserVo() {
		return null;
	}
	
	@GetMapping(value = {"/", "/index"})
	public String index(Model model, 
						@ModelAttribute("sessUser") UserVo sessUser) {
		
		List<ArticleVo> latests1 = service.selectLatests("grow");
		List<ArticleVo> latests2 = service.selectLatests("school");
		List<ArticleVo> latests3 = service.selectLatests("story");
		
		List<ArticleVo> latests4 = service.selectCumuLatests("notice");
		List<ArticleVo> latests5 = service.selectCumuLatests("qna");
		List<ArticleVo> latests6 = service.selectCumuLatests("faq");
		
		model.addAttribute("latests1", latests1);
		model.addAttribute("latests2", latests2);
		model.addAttribute("latests3", latests3);

		model.addAttribute("latests4", latests4);
		model.addAttribute("latests5", latests5);
		model.addAttribute("latests6", latests6);

		return "/index";
	}
}