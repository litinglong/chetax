package com.silva.chetax.demo.design.patterns.behavior.command;

/**
 * 具体命令
 * 
 * @author Administrator
 *
 */
public class ConcreteCommand implements Command {
	private Receiver receiver;

	ConcreteCommand() {
		receiver = new Receiver();
	}

	public void execute() {
		receiver.action();
	}
}
