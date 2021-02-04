package com.silva.chetax.demo.design.patterns.structural.decorator;

/**
 * 抽象装饰角色
 * @author Administrator
 *
 */
public class Decorator implements Component {
	private Component component;

	public Decorator(Component component) {
		this.component = component;
	}

	public void operation() {
		component.operation();
	}
}
