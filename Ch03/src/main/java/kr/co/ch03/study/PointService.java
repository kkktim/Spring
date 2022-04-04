package kr.co.ch03.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {
	
	@Autowired
	private LogAdvice advice;
	
	public void insert() {
		System.out.println("핵심관심 - insert...");
	}
	
	public void select() {
		System.out.println("핵심관심 - select...");
	}
	
	public void update() {
		System.out.println("핵심관심 - update...");
	}
	
	public void delete() {
		advice.beforeAdvice();
		System.out.println("핵심관심 - delete...");
		advice.afterAdvice();
	}
}
