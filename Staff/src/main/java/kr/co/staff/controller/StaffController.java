package kr.co.staff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.staff.service.StaffService;
import kr.co.staff.vo.StaffVo;

@Controller
public class StaffController {

	@Autowired
	private StaffService service; 
	//등록
	@GetMapping("/register")
	public String register() {
		return "/register";
	}
	@PostMapping("/register")
	public String register(StaffVo sv) {
		service.insertStaff(sv);
		return "redirect:/register";
	}
	//목록
	@GetMapping("/list")
	public String list(Model model) {
		List<StaffVo> staffs = service.selectStaffs();
		model.addAttribute("staffs", staffs);
		return "/list";
	}
	//수정
	@GetMapping("/modify")
	public String modify(int stf_no, Model model) {
		StaffVo staff = service.selectStaff(stf_no);
		model.addAttribute("staff", staff);
		return "/modify";
	}
	@PostMapping("/modify")
	public String modify(StaffVo sv) {
		service.updateStaff(sv);
		return "redirect:/list";
	}
	//삭제
	@GetMapping("/delete")
	public String delete(int stf_no) {
		service.deleteStaff(stf_no);
		return "/delete";
	}
}
