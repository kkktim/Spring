package kr.co.farmstory.vo;

import lombok.Data;

@Data
public class FileVo {

	private int fid;
	private int parent;
	private String oName;
	private String nName;
	private int download;
	private String rdate;
	
}
