package kr.co.ch02.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 날짜 : 2022/04/04
 * 이름 : 강태호
 * 내용 : Ch02.스프링 IoC/DI 실습하기
 * 
 * IoC/DI
 *  - 자바 객체(POJO)를 생성하고 객체들 사이의 의존관계를 스프링 컨테이너로 처리하는 개념
 *  - IoC를 구현하는 기술이 DI(Dependency Injection)
 *	- DI기법 가운데 Annotation 기법을 가장 많이 사용
 *
 * Annotation 종류
 *  @component
 *   - 스프링 컨테이너가 해당 어노테이션이 선언된 클래스를 생성/관리
 *   - @controller, @Service, @Repository 등 세분화
 *   
 *  @Autowired
 *   - 참조변수(객체)에 스프링 컨테이너로 부터 해당 객체를 찾아서 주입해주는 어노테이션
 */

@RestController
public class IoCTest {
	
	@GetMapping("/test1")
	public void test1() {
		System.out.println("----------- test1 -----------");
		
		//IoC/DI 적용하지 않을 경우
		Speaker spk = new Speaker();
		Tv tv = new Tv(spk);
		
		tv.powerOn();
		tv.chUp();
		tv.soundUp();
		tv.powerOff();
		
	}
	
	@Autowired
	private Tv tv;
	
	@GetMapping("/test2")
	public void test2() {
		System.out.println("----------- test2 -----------");
		
		//IoC/DI 적용할 경우
		tv.powerOn();
		tv.chUp();
		tv.soundUp();
		tv.powerOff();
		
	}
}
