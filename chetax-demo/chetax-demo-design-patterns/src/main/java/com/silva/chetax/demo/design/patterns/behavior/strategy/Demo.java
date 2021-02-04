package com.silva.chetax.demo.design.patterns.behavior.strategy;

/**
 * http://c.biancheng.net/view/1378.html
 * 
 * <p> 1. 策略模式的结构
 * <p> 策略模式的主要角色如下。
 * <p> 抽象策略（Strategy）类：定义了一个公共接口，各种不同的算法以不同的方式实现这个接口，环境角色使用这个接口调用不同的算法，一般使用接口或抽象类实现。
 * <p> 具体策略（Concrete Strategy）类：实现了抽象策略定义的接口，提供具体的算法实现。
 * <p> 环境（Context）类：持有一个策略类的引用，最终给客户端调用。
 * 
 * @author Administrator
 *
 */
public class Demo {
	public static void main(String[] args) {
		Strategy strategy = new OperationAdd();
		Context context = new Context();
		context.setStrategy(strategy);
		System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

		strategy = new OperationSubtract();
		context.setStrategy(strategy);
		System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

		strategy = new OperationMultiply();
		context.setStrategy(strategy);
		System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
	}
}
