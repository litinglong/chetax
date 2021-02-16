package com.silva.chetax.spring.life.test.demo.reflection;

public enum AnnotationPosition {
	DEFAULT("DEFAULT"),CLAZZ("CLAZZ"),CONSTRUCTOR("CONSTRUCTOR"),FIELD("FIELD"),METHOD("METHOD");
	
	private String code;
	
	AnnotationPosition(String code){
		this.code = code;
	}
	
	public String getCode(){
		return this.code;
	}
}
