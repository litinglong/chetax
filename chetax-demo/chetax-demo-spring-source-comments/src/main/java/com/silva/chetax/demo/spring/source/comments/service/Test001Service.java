package com.silva.chetax.demo.spring.source.comments.service;

public interface Test001Service {
	void test001();
	String test002() throws InterruptedException ;
	
	default String test003() throws InterruptedException {
		return this.getClass().getName();
	}
}
