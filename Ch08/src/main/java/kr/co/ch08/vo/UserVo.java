package kr.co.ch08.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="User2")
public class UserVo {

	@Id
	private String uid;
	
	private String pass;
	private String name;
	private String hp;
	private int age;
	
}
