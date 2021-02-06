package com.silva.chetax.spring.life.test.demo.inherit;

public class Lv4 extends Lv3{
	private String markLv4;
	Lv4(){
		super("");
		System.out.println("Lv4 non-args");
	}
	
	Lv4(String markLv1){
		System.out.println("Lv4 args");
	}
}
