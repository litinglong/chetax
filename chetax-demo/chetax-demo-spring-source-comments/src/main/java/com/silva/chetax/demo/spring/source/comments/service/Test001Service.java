package com.silva.chetax.demo.spring.source.comments.service;

public interface Test001Service {
	void test001();
	String test002();
	
	default String test003() {
		return this.getClass().getName();
	}
}
