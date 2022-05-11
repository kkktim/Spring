package kr.co.kmarket.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartVo {

	private int cid;
	private String uid;
	private int pid;
	private int count;
	private int price;
	private int discount;
	private int point;
	private int delivery;
	private int total;
	private String rdate;
	
	//추가필드
	private int cate1;
	private int cate2;
	private String name;
	private String desc;
	private String thumb1;
}
