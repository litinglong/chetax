package com.silva.chetax.spring.life.test.demo.concurrent;

public class ValueObject {
	private int value=0;
	static {
		System.out.println("ValueObject static block");
	}
	static void staticBlockTest1() {
	}
	public void selfAdd(){
		value++;
	}
	@Override
	public String toString() {
		return "ValueObject [value=" + value + "]";
	}
	
}
