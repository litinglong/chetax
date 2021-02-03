package com.silva.chetax.demo.design.patterns.creational.factory.abstracts;

/**
 * http://c.biancheng.net/view/1351.html
 * <p>https://www.runoob.com/design-pattern/abstract-factory-pattern.html
 * 
 * <p>
 * 抽象工厂模式的主要角色如下。
 * <p>
 * 抽象工厂（Abstract Factory）：提供了创建产品的接口，它包含多个创建产品的方法 newProduct()，可以创建多个不同等级的产品。
 * <p>
 * 具体工厂（Concrete Factory）：主要是实现抽象工厂中的多个抽象方法，完成具体产品的创建。
 * <p>
 * 抽象产品（Product）：定义了产品的规范，描述了产品的主要特性和功能，抽象工厂模式有多个抽象产品。
 * <p>
 * 具体产品（ConcreteProduct）：实现了抽象产品角色所定义的接口，由具体工厂来创建，它同具体工厂之间是多对一的关系。
 * <p>
 * 扩展时，提供一套对抽象产品和抽象工厂的实现类即可。
 * @author Administrator
 *
 */
public class Demo {
	public static void main(String[] args) {
		FarmFactory f;
		ProductAnimal a;
		ProductPlant p;
		System.out.println("SGfarmFactory开始表演");
		f = FarmFactory.createFactory("SG");
		a = f.newAnimal();
		a.show();
		p = f.newPlant();
		p.show();
		System.out.println();
		
		System.out.println("SRfarmFactory开始表演");
		f = FarmFactory.createFactory("SR");
		a = f.newAnimal();
		a.show();
		p = f.newPlant();
		p.show();
	}
}
