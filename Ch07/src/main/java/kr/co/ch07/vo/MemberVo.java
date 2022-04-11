package kr.co.ch07.vo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="Member")
public class MemberVo {
	@Id
	private String uid;
	
	private String name;
	private String hp;
	private String pos;
	private int dep;
	
	@Column(updatable=false)
	@CreationTimestamp
	private LocalDateTime rdate;
	
}
