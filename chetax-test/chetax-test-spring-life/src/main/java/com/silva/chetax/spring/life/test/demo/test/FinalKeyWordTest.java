package com.silva.chetax.spring.life.test.demo.test;

public class FinalKeyWordTest {
	
	public static void main(String[] args) {
		// 类对象的声明被final标识后，则该引用不能被更改，但引用指向的目标实例的内部属性是可以更改的
		final FinalKeyWordTest o = new FinalKeyWordTest();
		o.setName("1");
		System.out.println(o.getName());
		o.setName("2");
		System.out.println(o.getName());
	}
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
