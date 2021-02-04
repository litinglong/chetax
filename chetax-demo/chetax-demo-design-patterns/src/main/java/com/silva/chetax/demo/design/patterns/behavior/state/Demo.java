package com.silva.chetax.demo.design.patterns.behavior.state;

/**
 * https://www.runoob.com/design-pattern/state-pattern.html
 * 
 * <p> 1. 模式的结构
 * <p> 状态模式包含以下主要角色。
 * <p> 环境类（Context）角色：也称为上下文，它定义了客户端需要的接口，内部维护一个当前状态，并负责具体状态的切换。
 * <p> 抽象状态（State）角色：定义一个接口，用以封装环境对象中的特定状态所对应的行为，可以有一个或多个行为。
 * <p> 具体状态（Concrete State）角色：实现抽象状态所对应的行为，并且在需要的情况下进行状态切换。
 * 
 * @author Administrator
 *
 */
public class Demo {
	public static void main(String[] args) {
		Context context = new Context();

		StartState startState = new StartState();
		startState.doAction(context);

		System.out.println(context.getState().toString());

		StopState stopState = new StopState();
		stopState.doAction(context);

		System.out.println(context.getState().toString());
	}
}
