package kr.co.farmstory.vo;

import java.sql.Timestamp;

import javax.persistence.Column;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
public class UserVo {

	private String uid;
	private String pass;
	private String name;
	private String nick;
	private String email;
	private String hp;
	private int grade;
	private String zip;
	private String addr1;
	private String addr2;
	private String regip;
	
	@Column(updatable = false)
	@CreationTimestamp
	private Timestamp rdate;
	
}
