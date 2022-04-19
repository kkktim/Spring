package kr.co.sboard1.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Entity
@Table(name = "Board_article")
public class ArticleVo {
	//JPA save 후 리턴 값 받아오기 위해서 @GeneratedValue(strategy = GenerationType.IDENTITY) 선언
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int no;
	
	private int parent;
	private int comment;
	private String type;
	private String title;
	private String content;
	private int file;
	private int hit;
	private String uid;
	private String regip;
	
	@Column(updatable=false)
	@CreationTimestamp
	private Timestamp rdate;
	
	//추가필드
	@Transient   //테이블의 컬럼을 해당 엔티티에서 제외하는 어노테이션 - java persistence
	private MultipartFile fname;
	
	//추가필드
	@Transient
	private String nick;
	
	@Transient
	private FileVo fv;
}
