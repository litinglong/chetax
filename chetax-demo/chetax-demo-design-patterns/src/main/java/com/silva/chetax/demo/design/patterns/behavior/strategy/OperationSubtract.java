package com.silva.chetax.demo.design.patterns.behavior.strategy;

public class OperationSubtract implements Strategy {
	@Override
	public int doOperation(int num1, int num2) {
		return num1 - num2;
	}
}