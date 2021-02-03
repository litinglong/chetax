package com.silva.chetax.demo.design.patterns.creational.builder;

/**
 * (4) 指挥者：调用建造者中的方法完成复杂对象的创建。
 * @author Administrator
 *
 */
public class Director {
	private Builder builder;

	public Director(Builder builder) {
		this.builder = builder;
	}

	// 产品构建与组装方法,此步骤可以任意组合
	public Product construct() {
		builder.buildPartA();
		builder.buildPartB();
		builder.buildPartC();
		return builder.getResult();
	}
}
