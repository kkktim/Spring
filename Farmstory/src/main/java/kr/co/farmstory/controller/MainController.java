package kr.co.farmstory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.farmstory.service.MainService;
import kr.co.farmstory.vo.ArticleVo;

@Controller
public class MainController {
	@Autowired
	private MainService service;
	
	@GetMapping(value = {"/", "/index"})
	public String index(Model model) {
		
		List<ArticleVo> latests1 = service.selectLatests("grow");
		List<ArticleVo> latests2 = service.selectLatests("school");
		List<ArticleVo> latests3 = service.selectLatests("story");
		
		model.addAttribute("latests1", latests1);
		model.addAttribute("latests2", latests2);
		model.addAttribute("latests3", latests3);

		return "/index";
	}
	
}