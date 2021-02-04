package com.silva.chetax.demo.design.patterns.behavior.command;

/**
 * 调用者
 * 
 * @author Administrator
 *
 */
public class Invoker {
	private Command command;

	public Invoker(Command command) {
		this.command = command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public void call() {
		System.out.println("调用者执行命令command...");
		command.execute();
	}
}