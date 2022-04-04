package kr.co.ch02.study;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class Remocon {
	public void powerOn() {
		System.out.println("Remocon powerOn!");
	}
	public void powerOff() {
		System.out.println("Remocon powerOff!");
	}
	public void chUp() {
		System.out.println("Remocon chUp!");
	}
	public void chDown() {
		System.out.println("Remocon chDown!");
	}
}
