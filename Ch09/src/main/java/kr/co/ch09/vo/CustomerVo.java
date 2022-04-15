package kr.co.ch09.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Customer")
public class CustomerVo {
	@Id
	private int custid;
	
	private String name;
	private String address;
	private String phone;
}
