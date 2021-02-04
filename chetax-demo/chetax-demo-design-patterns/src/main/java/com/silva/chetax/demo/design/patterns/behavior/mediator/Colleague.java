package com.silva.chetax.demo.design.patterns.behavior.mediator;

/**
 * 抽象同事类
 * @author Administrator
 *
 */
public abstract class Colleague {
	protected Mediator mediator;

	public void setMedium(Mediator mediator) {
		this.mediator = mediator;
	}

	public abstract void receive();

	public abstract void send();
}
