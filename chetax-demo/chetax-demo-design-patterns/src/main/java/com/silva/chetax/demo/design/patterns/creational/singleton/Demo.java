package com.silva.chetax.demo.design.patterns.creational.singleton;

public class Demo {

	public static void main(String[] args) {
		SingleObject object = SingleObject.getInstance();
		object.showMessage();
	}

}
