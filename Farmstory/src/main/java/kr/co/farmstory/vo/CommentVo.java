package kr.co.farmstory.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentVo {

	private int no;
	private int parent;
	private String content;
	private String uid;
	private String regip;
	private String rdate;
	private String nick;
	
}
