package com.silva.chetax.demo.design.patterns.behavior.mediator;

/**
 * 具体同事类
 * @author Administrator
 *
 */
public class ConcreteColleague1 extends Colleague {
	public void receive() {
		System.out.println("具体同事类1收到请求。");
	}

	public void send() {
		System.out.println("具体同事类1发出请求。");
		mediator.relay(this); // 请中介者转发
	}
}
