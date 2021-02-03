package com.silva.chetax.demo.design.patterns.creational.factory.simple;

public enum Const {
	PRODUCT_A(1), PRODUCT_B(2), PRODUCT_OTHER(3);

	private int code;
	
	Const(int code) {
		this.code = code;
	}
	
	public int getCode(){
		return this.code;
	}

}
