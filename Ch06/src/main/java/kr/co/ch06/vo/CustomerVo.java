package kr.co.ch06.vo;

import lombok.Data;

@Data   //@Data = @Getter + @Setter
public class CustomerVo {

	private int custid;
	private String name;
	private String address;
	private String phone;
	
}
