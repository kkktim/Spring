package kr.co.kmarket.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "km_product")
public class ProductEntity {
	@Id
	private int pid;
	private int cate1;
	private int cate2;
	private String name;
	private String desc;
	private String company;
	private String seller;
	private int price;
	private int discount;
	private int point;
	private int stock;
	private int sold;
	private int delivery;
	private int hit;
	private int score;
	private int review;
	private String thumb1;
	private String thumb2;
	private String thumb3;
	private String detail;
	private String status;
	private String dutyfree;
	private String receipt;
	private String bizClass;
	private String origin;
	private String ip;
	private String rdate;
	private int etc1;
	private int etc2;
	private String etc3;
	private String etc4;
	private String etc5;
	
	//추가필드
	@Transient
	private int salePrice;
}
