package com.silva.chetax.demo.design.patterns.template;

public class GameToolSimpleOne extends AbstractGameTool {

	@Override
	protected void selectGameToPlay() {
		System.out.println("GameToolSimpleOne selectGameToPlay");
	}

	@Override
	protected void playing() {
		System.out.println("GameToolSimpleOne playing");
	}

}
