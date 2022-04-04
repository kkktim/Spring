package kr.co.ch02.study;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Speaker {
	
	public void soundUp() {
		System.out.println("Speaker soundUp");
	}
	public void soundDown() {
		System.out.println("Speaker soundDown");
	}
}
