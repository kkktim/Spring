package kr.co.ch07.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="User2")   //table 이름
public class UserVo {
	
	@Id   //PK
	private String uid;
	
	private String name;
	private String hp;
	private int age;
	
}
