package com.silva.chetax.demo.design.patterns.template;

public abstract class AbstractGameTool {
	public void play() {
		readyToplay();
		selectGameToPlay();
		joinTheGame();
		playing();
		endGame();
	}

	private final void readyToplay() {
		System.out.println("readyToplay");
	}

	abstract protected void selectGameToPlay();

	private final void joinTheGame() {
		System.out.println("joinTheGame");
	}

	abstract protected void playing();

	private final void endGame() {
		System.out.println("endGame");
	}
}
