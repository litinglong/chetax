package com.silva.chetax.demo.design.patterns.behavior.chain;

import java.math.BigDecimal;

import com.silva.chetax.demo.design.patterns.behavior.chain.handler.CEOHandler;
import com.silva.chetax.demo.design.patterns.behavior.chain.handler.DirectorHandler;
import com.silva.chetax.demo.design.patterns.behavior.chain.handler.ManagerHandler;

/**
 * https://www.liaoxuefeng.com/wiki/1252599548343744/1281319474561057
 * <p>
 * 1. 模式的结构
 * <p>
 * 职责链模式主要包含以下角色。
 * <p>
 * 抽象处理者（Handler）角色：定义一个处理请求的接口，包含抽象处理方法和一个后继连接。
 * <p>
 * 具体处理者（Concrete Handler）角色：实现抽象处理者的处理方法，判断能否处理本次请求，如果可以处理请求则处理，否则将该请求转给它的后继者。
 * <p>
 * 客户类（Client）角色：创建处理链，并向链头的具体处理者对象提交请求，它不关心处理细节和请求的传递过程。
 * 
 * @author Administrator
 *
 */
public class Demo {
	public static void main(String[] args) {
		// 构造责任链:
		HandlerChain chain = new HandlerChain();
		chain.addHandler(new ManagerHandler());
		chain.addHandler(new DirectorHandler());
		chain.addHandler(new CEOHandler());
		// 处理请求:
		System.out.println("Bob start:");
		chain.process(new Request("Bob", new BigDecimal("123.45")));
		System.out.println("Alice start:");
		chain.process(new Request("Alice", new BigDecimal("1234.56")));
		System.out.println("Bill start:");
		chain.process(new Request("Bill", new BigDecimal("1234.67")));
		System.out.println("Bill start:");
		chain.process(new Request("Bill", new BigDecimal("12345.67")));
		System.out.println("John start:");
		chain.process(new Request("John", new BigDecimal("123456.78")));
	}
}
