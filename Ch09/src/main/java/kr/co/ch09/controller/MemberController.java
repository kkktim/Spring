package kr.co.ch09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ch09.service.MemberService;
import kr.co.ch09.vo.MemberVo;

//RestController 하면 ResponseBody 된 거임
@RestController
public class MemberController {
	@Autowired
	private MemberService service;
	//목록
	@GetMapping("/member")
	public List<MemberVo> list() {
		List<MemberVo> members = service.selectMembers();
		return members;
	}
	//등록
	@PostMapping("/member")
	public List<MemberVo> register(MemberVo mv) {
		System.out.println(mv.getUid());
		service.insertMember(mv);
		return service.selectMembers();
	}
	//수정
	@PutMapping("/member/{uid}")
	public List<MemberVo> modify(@PathVariable("uid") String uid, MemberVo mv) {
		mv.setUid(uid);
		service.updateMember(mv);
		return service.selectMembers();
	}
	//삭제
	@DeleteMapping("/member/{uid}")
	public List<MemberVo> delete(@PathVariable("uid") String uid) {
		service.deleteMember(uid);
		return service.selectMembers();
	}
}
