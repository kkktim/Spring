package kr.co.ch05.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

	private String uid;
	private String name;
	private String hp;
	private int age;
	
	//NoArgsConstructor
//	public UserVo() {
//		
//	}
	//AllArgsConstructor
//	public UserVo(String uid, String name, String hp, int age) {
//		this.uid = uid;
//		this.name = name;
//		this.hp = hp;
//		this.age = age;
//	}
}
