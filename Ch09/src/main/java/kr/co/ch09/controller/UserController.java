package kr.co.ch09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ch09.service.UserService;
import kr.co.ch09.vo.UserVo;

@Controller
public class UserController {
	@Autowired
	private UserService service;
	//리스트
	//ResponseBody 를 해줘야 데이터가 클라이언트로 바로 감
	@ResponseBody
	@GetMapping("/user")
	public List<UserVo> list() {
		List<UserVo> users = service.selectUsers();
		return users;
	}
	//등록
	@ResponseBody
	@PostMapping("/user")
	public List<UserVo> register(UserVo uv) {
		service.insertUser(uv);
		return service.selectUsers();
	}
	//수정
	//예전 방식 - @RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	@PutMapping("/user/{uid}")
	public List<UserVo> modify(@PathVariable("uid") String uid, UserVo uv) {
		//주소에 있는 {uid} 를 String uid로 받음
		uv.setUid(uid);
		service.updateUser(uv);
		return service.selectUsers();
	}
	
	//삭제
	@ResponseBody
	@DeleteMapping("/user/{uid}")
	public List<UserVo> delete(@PathVariable("uid") String uid) {
		service.deleteUser(uid);
		return service.selectUsers();
	}
}
