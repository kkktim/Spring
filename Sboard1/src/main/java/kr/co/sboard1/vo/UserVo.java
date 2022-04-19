package kr.co.sboard1.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "Board_user")
public class UserVo {
	@Id
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
	
	@Column(updatable=false)
	@CreationTimestamp
	private Timestamp rdate;
	
	//이것도 되는건데 안됨 - 나중에 체크
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date rdate;
//	//Date = java.util
	
}
