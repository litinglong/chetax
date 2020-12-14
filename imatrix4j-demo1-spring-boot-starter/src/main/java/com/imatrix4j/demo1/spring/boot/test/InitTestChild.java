package com.imatrix4j.demo1.spring.boot.test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InitTestChild extends InitTestParent{
	{
		System.out.println("child block1");
	}
	{
		System.out.println("child block2");
	}
	static{
		System.out.println("child block1 static");
	}
	static{
		System.out.println("child block2 static");
	}
	
	private InitTestParent parentBean;
	
	InitTestChild() {
		System.out.println("child constructor 1");
	}
	
	InitTestChild(String name) {
		super();
		System.out.println("child constructor 2");
		this.parentBean = new InitTestParent("parentBean");
	}
}
