package kr.co.farmstory.vo;

import lombok.Data;

@Data
public class CommentVo {

	private int parent;
	private String content;
	private String regip;
	private String uid;
	private String rdate;

	//추가필드
	private String nick;
}
