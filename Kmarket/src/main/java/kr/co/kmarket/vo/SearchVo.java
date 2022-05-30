package kr.co.kmarket.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchVo {

	private String keyword;
	private int orderby;
	private String chk;
	private String min;
	private String max;
	
	
	//fulltext 추가필드
//	private String[] columns;
//
//	public void setColumns(String chk1, String chk2) {
//		
//	}
}
