package com.silva.chetax.spring.life.test.demo.inherit;

/**
 * 抽象类
 * 如果定义了有参构造，最好显示定义无参构造
 * 
 * @author litinglong
 *
 */
public abstract class AbstractLv1 {
	private String markLv1;
	AbstractLv1(){
		System.out.println("AbstractLv1 non-args");
	}
	
	AbstractLv1(String markLv1){
		System.out.println("AbstractLv1 args");
	}
}
