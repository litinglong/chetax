package com.silva.chetax.spring.life.test.demo.inherit;

public class Lv2 extends AbstractLv1{
	private String markLv2;
	
	{
		System.out.println("Lv2 common-block1");
	}
	{
		System.out.println("Lv2 common-block2");
	}
	
	static{
		System.out.println("Lv2 static-block1");
	}
	static{
		System.out.println("Lv2 static-block2");
	}
	
	Lv2(){
		System.out.println("Lv2 non-args");
	}
	
	Lv2(String markLv1){
		System.out.println("Lv2 args");
	}
}
