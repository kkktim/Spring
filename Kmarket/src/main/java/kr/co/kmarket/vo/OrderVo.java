package kr.co.kmarket.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderVo {

	private int oid;
	private String uid;
	private int count;
	private int price;
	private int discount;
	private int point;
	private int usePoint;
	private int delivery;
	private int total;
	private String orderer;
	private String hp;
	private String zip;
	private String addr1;
	private String addr2;
	private int payment;
	private int complete;
	private String rdate;
	private String completeDate;
	
	//추가필드
	private int[] cids;
	private int[] pids;
	private int[] counts;
	
	private int pid;
	private int cnt;
	
	private int pCate1;
	private int pCate2;
	private String pName;
	private String pDesc;
	private String pThumb1;
	private int pPrice;
	private int pDiscount;
	private int pPoint;
	private int pDelivery;
	private int pSalePrice;
	private int dPrice;
	
	private String uName;
	private String uHp;
	private String uZip;
	private String uAddr1;
	private String uAddr2;
	private int uPoint;
	
	private int savePoint;
	
}
