package kr.co.ch06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.ch06.service.StudentService;
import kr.co.ch06.vo.StudentVo;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService service;
	//등록
	@GetMapping("/student/register")
	public String register() {
		return "/student/register";
	}
	
	@PostMapping("/student/register")
	public String register(StudentVo sv) {
		service.insertStudent(sv);
		return "redirect:/student/list";
	}
	//리스트
	@GetMapping("/student/list")
	public String list(Model model) {
		List<StudentVo> students = service.selectStudents();
		model.addAttribute("students", students);
		return "/student/list";
	}
	//수정
	@GetMapping("/student/modify")
	public String modify(String sid, Model model) {
		StudentVo sv = service.selectStudent(sid);
		model.addAttribute("sv", sv);
		return "/student/modify";
	}
	@PostMapping("/student/modify")
	public String modify(StudentVo sv, Model model) {
		int result = service.updateStudent(sv);
		model.addAttribute("result", result);
		return "redirect:/student/list";
	}
	//삭제
	@GetMapping("/student/delete")
	public String delete(String sid) {
		service.deleteStudent(sid);
		return "redirect:/student/list";
	}
}
