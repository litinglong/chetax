package com.silva.chetax.demo.design.patterns.creational.factory.common;

/**
 * http://c.biancheng.net/view/1348.html
 * 
 * <p> 1. 模式的结构
 * <p> 工厂方法模式的主要角色如下。
 * <p> 抽象工厂（Abstract Factory）：提供了创建产品的接口，调用者通过它访问具体工厂的工厂方法 newProduct() 来创建产品。
 * <p> 具体工厂（ConcreteFactory）：主要是实现抽象工厂中的抽象方法，完成具体产品的创建。
 * <p> 抽象产品（Product）：定义了产品的规范，描述了产品的主要特性和功能。
 * <p> 具体产品（ConcreteProduct）：实现了抽象产品角色所定义的接口，由具体工厂来创建，它同具体工厂之间一一对应。
 * 
 * @author Administrator
 *
 */
public class Demo {
	public static void main(String[] args) {
		AbstractFactory af;
		Product p;
		af = new ConcreteFactory1();
		p = af.newProduct();
		p.show();

		af = new ConcreteFactory2();
		p = af.newProduct();
		p.show();
	}
}
