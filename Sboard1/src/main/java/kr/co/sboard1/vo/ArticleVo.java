package kr.co.sboard1.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


@Data
public class ArticleVo {
	//JPA save 후 리턴 값 받아오기 위해서 @GeneratedValue(strategy = GenerationType.IDENTITY) 선언
	private int no;
	
	private int parent;
	private int comment;
	private String type;
	private String title;
	private String content;
	private int file;
	private int hit;
	
//	@Column(name = "ARTICLE_UID")
	private String uid;
	private String regip;
	
	//리스트에 [[${article.rdate.substring()}]] 에 출력이 안되서 @getter 어노테이션 함
//	@Getter(value = AccessLevel.NONE)   //해당 필드를 Lombok Getter 생성에서 제외
	@Column(updatable=false)   //업데이트에서 제외
	@CreationTimestamp
	private Timestamp rdate;
	//rdate 스트링으로 변환
//	public String getRdate() {
//		return rdate.toString().substring(2, 10);
//	}
	
	//추가필드
	@Transient   //테이블의 컬럼을 해당 엔티티에서 제외하는 어노테이션 - java persistence
	private MultipartFile fname;
	
	//추가필드 - 먼저 설정 안하면 디비에 칼럼 하나 더 생김
	@Transient
	private String nick;
	
	//추가필드
//	@Transient
	private FileVo fv;
	
//	@OneToOne
//	@JoinTable(name = "Board_user",
//		joinColumns = @JoinColumn(name = "USER_UID"),
//		inverseJoinColumns = @JoinColumn(name = "ARTICLE_UID"))
//	private UserVo uv;
	
}
