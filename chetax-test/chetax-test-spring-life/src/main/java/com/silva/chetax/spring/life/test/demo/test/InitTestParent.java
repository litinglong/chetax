package com.silva.chetax.spring.life.test.demo.test;

public class InitTestParent {
	{
		System.out.println("parent block1");
	}
	{
		System.out.println("parent block2");
	}
	static{
		System.out.println("parent block1 static");
	}
	static{
		System.out.println("parent block2 static");
	}
	private String name;
	
	InitTestParent(){
		System.out.println("parent constructor 1");
	}
	InitTestParent(String name){
		this.name = name;
		System.out.println("parent constructor 2");
	}
}
