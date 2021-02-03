package com.silva.chetax.demo.design.patterns.creational.factory.simple;

/**
 * http://c.biancheng.net/view/8385.html
 * 
 * <p> 简单工厂模式的主要角色如下：
 * <p> 简单工厂（SimpleFactory）：是简单工厂模式的核心，负责实现创建所有实例的内部逻辑。工厂类的创建产品类的方法可以被外界直接调用，创建所需的产品对象。
 * <p> 抽象产品（Product）：是简单工厂创建的所有对象的父类，负责描述所有实例共有的公共接口。
 * <p> 具体产品（ConcreteProduct）：是简单工厂模式的创建目标。
 * 
 * @author Administrator
 *
 */
public class Demo {
	public static void main(String[] args) {
		Product a = SimpleFactory.makeProduct(Const.PRODUCT_A);
		Product b = SimpleFactory.makeProduct(Const.PRODUCT_B);
		a.show();
		b.show();
	}

}
