package com.silva.chetax.demo.design.patterns.structural.decorator;

/**
 * 具体构件角色
 * @author Administrator
 *
 */
public class ConcreteComponent implements Component {
	public ConcreteComponent() {
		System.out.println("创建具体构件角色");
	}

	public void operation() {
		System.out.println("调用具体构件角色的方法operation()");
	}
}
