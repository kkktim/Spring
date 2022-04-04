package kr.co.ch03.study;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class LogAdvice {
	
	@Pointcut("execution(* kr.co.ch03.study.PointService.insert*(..))")
	public void insertPointCut() {}   // 내용이 없는 참조메서드
	@Before("insertPointCut()")
	public void beforeAdvice() {
		System.out.println("횡단관심 - beforeAdvice...");
	}
	@After("insertPointCut()")
	public void afterAdvice() {
		System.out.println("횡단관심 - afterAdvice...");
	}
	
	@Pointcut("execution(* kr.co.ch03.study.PointService.select*(..))")
	public void selectPointCut() {}
	@Before("selectPointCut()")
	public void beforeAdviceselect() {
		System.out.println("횡단관심 - beforeAdvice...");
	}
	@After("selectPointCut()")
	public void afterAdviceselect() {
		System.out.println("횡단관심 - afterAdvice...");
	}
	
	@Pointcut("execution(* kr.co.ch03.study.PointService.update*(..))")
	public void updatePointCut() {}
	@Before("updatePointCut()")
	public void beforeAdviceUpdate() {
		System.out.println("횡단관심 - beforeAdvice...");
	}
	@After("updatePointCut()")
	public void afterAdviceUpdated() {
		System.out.println("횡단관심 - afterAdvice...");
	}
}
