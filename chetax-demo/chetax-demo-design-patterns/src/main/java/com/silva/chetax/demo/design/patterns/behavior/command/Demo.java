package com.silva.chetax.demo.design.patterns.behavior.command;

/**
 * http://c.biancheng.net/view/1380.html
 * <p>
 * 1. 模式的结构
 * <p>
 * 命令模式包含以下主要角色。
 * <p>
 * 抽象命令类（Command）角色：声明执行命令的接口，拥有执行命令的抽象方法 execute()。
 * <p>
 * 具体命令类（Concrete Command）角色：是抽象命令类的具体实现类，它拥有接收者对象，并通过调用接收者的功能来完成命令要执行的操作。
 * <p>
 * 实现者/接收者（Receiver）角色：执行命令功能的相关操作，是具体命令对象业务的真正实现者。
 * <p>
 * 调用者/请求者（Invoker）角色：是请求的发送者，它通常拥有很多的命令对象，并通过访问命令对象来执行相关请求，它不直接访问接收者。
 * 
 * @author Administrator
 *
 */
public class Demo {
	public static void main(String[] args) {
		Command cmd = new ConcreteCommand();
		Invoker ir = new Invoker(cmd);
		System.out.println("客户访问调用者的call()方法...");
		ir.call();
	}
}
