package kr.co.ch07.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Students")
public class StudentVo {

	@Id
	private String sid;
	
	private String name;
	private int gender;
	private String hp;
	private String grade;
	private String regdate;
	
}
