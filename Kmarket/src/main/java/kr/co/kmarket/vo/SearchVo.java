package kr.co.kmarket.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchVo {

	private String keyword;
	private int orderby;
	private String chk1;
	private String chk2;
	private String chk3;
	private int min;
	private int max;
	
	
	//fulltext 추가필드
//	private String[] columns;
//
//	public void setColumns(String chk1, String chk2) {
//		
//	}
}
