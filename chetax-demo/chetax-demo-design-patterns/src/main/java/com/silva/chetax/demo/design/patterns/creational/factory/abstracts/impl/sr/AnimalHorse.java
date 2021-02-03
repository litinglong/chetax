package com.silva.chetax.demo.design.patterns.creational.factory.abstracts.impl.sr;

import com.silva.chetax.demo.design.patterns.creational.factory.abstracts.ProductAnimal;

public class AnimalHorse implements ProductAnimal {

	public AnimalHorse() {
		System.out.println("动物：马");
	}

	public void show() {
		System.out.println("动物：马：落地");
	}
}
