package kr.co.ch03.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/*
 * 날짜 : 2022/04/04
 * 이름 : 강태호
 * 내용 : 스프링 AOP 실습하기
 * 
 * 스프링 AOP(Aspect Oriented Programming)
 *  - AOP는 관점지향 프로그래밍으로 OOP로 독립적으로 분리하기 어려운 부가기능 로직을 모듈화하는 방식
 *  - 핵심관심과 전체에 적용되는 공통관심을 분리해서 프로그래밍하는 방식
 *  - spring-boot-starter-app 의존 라이브러리 추가할 것
 *  
 * 조인포인트(JoinPoint)
 *  - 실행하는 모든 핵심관심 메서드(포인트컷)
 *  
 * 포인트컷(Pointcut)
 *  - 조인포인트 가운데 실행되는 핵심관심 메서드
 * 
 * 어드바이스(Advice)
 *  - 횡단관심에 해당하는 공통의 부가기능 메서드
 *  
 * 에스펙트(Aspect)
 *  - 포인트컷과 어드바이스의 결합된 모듈형태
 *  
 * 위빙(Weaving)
 *  - 포인트컷이 실행될 때, 어드바이스가 포인트컷에 결합되는 과정
 *  
 * 포인트컷 표현식
 *  - execution(리턴타입 패키지명.클래스명.메서드명(매개변수))
 *  
 *  1) 리턴타입
 *   - * : 모든 리턴타입 허용
 *   - void : 리턴타입이 void인 메서드
 *   - !void : 리턴타입이 void가 아닌 메서드
 *   
 *  2) 패키지명
 *   - kr.co.ch03 : 해당 패키지 대상
 *   - kr.co.ch03.. : kr.co.ch03로 시작하는 모든 패키지 대상
 *   - kr.co.ch03..service : kr.co.ch03로 시작해서 마지막 패키지명이 service로 끝나는 패키지 대상
 *   
 *  3) 클래스명
 *   - BoardService : 해당 클래스 대상
 *   - *Service : 클래스명이 Service로 끝나는 클래스 대상
 *   
 *  4) 메서드명
 *   - *(..) : 매개변수가 제한이 없는 모든 메서드
 *   - *(*) : 매개변수를 1개 갖는 모든 메서드
 *   - *(*,*) : 매개변수를 2개 갖는 모든 메서드
 *   - get*() : 매개변수가 없고 메서드 이름이 get으로 시작하는 메서드
 */
@RestController
public class AOPTest {
	
	@Autowired
	private PointService service;
	
	@GetMapping("/test1")
	public void test1() {
		System.out.println("-------- AOP insert --------");
		service.insert();
		System.out.println("------------ End -----------");
	}

	@GetMapping("/test2")
	public void test2() {
		System.out.println("-------- AOP select --------");
		service.select();
		System.out.println("------------ End -----------");
	}

	@GetMapping("/test3")
	public void test3() {
		System.out.println("-------- AOP update --------");
		service.update();
		System.out.println("------------ End -----------");
	}
	
	@GetMapping("/test4")
	public void test4() {
		System.out.println("------- NOT AOP delete -------");
		service.delete();
		System.out.println("------------ End ------------");
	}
}
