package com.silva.chetax.spring.life.test.demo.inherit;

public class Lv2 extends AbstractLv1{
	private String markLv2;
	Lv2(){
		System.out.println("Lv2 non-args");
	}
	
	Lv2(String markLv1){
		System.out.println("Lv2 args");
	}
}
