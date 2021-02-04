package com.silva.chetax.demo.design.patterns.behavior.mediator;

/**
 * 抽象中介者
 * @author Administrator
 *
 */
public abstract class Mediator {
	public abstract void register(Colleague colleague);

	public abstract void relay(Colleague cl); // 转发
}
