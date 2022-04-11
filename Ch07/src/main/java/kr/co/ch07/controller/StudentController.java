package kr.co.ch07.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.ch07.service.StudentService;
import kr.co.ch07.vo.StudentVo;

@Controller
public class StudentController {
	@Autowired
	private StudentService service;
	//register
	@GetMapping("/student/register")
	public String register() {
		return "/student/register";
	}
	@PostMapping("/student/register")
	public String register(StudentVo sv) {
		service.insertStudent(sv);
		return "redirect:/student/list";
	}
	
	//list
	@GetMapping("/student/list")
	public String list(@RequestParam(defaultValue = "0") int result, Model model) {
		List<StudentVo> students = service.selectStudents();
		model.addAttribute("students", students);
		model.addAttribute("result", result);
		return "/student/list";
	}
	
	//modify
	@GetMapping("/student/modify")
	public String modify(String sid, Model model) {
		StudentVo student = service.selectStudent(sid);
		model.addAttribute("student", student);
		return "/student/modify";
	}
	@PostMapping("/student/modify")
	public String modify(StudentVo sv) {
		StudentVo student = service.updateStudent(sv);
		int result = 0;
		if(student != null) {
			result = 1;
		}
		return "redirect:/student/list?result="+result;
	}
	
	//delete
	@GetMapping("/student/delete")
	public String delete(String sid) {
		service.deleteStudent(sid);
		return "redirect:/student/list";
	}
}
