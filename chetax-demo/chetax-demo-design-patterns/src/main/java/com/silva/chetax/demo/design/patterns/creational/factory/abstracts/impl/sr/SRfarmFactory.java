package com.silva.chetax.demo.design.patterns.creational.factory.abstracts.impl.sr;

import com.silva.chetax.demo.design.patterns.creational.factory.abstracts.ProductAnimal;
import com.silva.chetax.demo.design.patterns.creational.factory.abstracts.FarmFactory;
import com.silva.chetax.demo.design.patterns.creational.factory.abstracts.ProductPlant;

public class SRfarmFactory implements FarmFactory {
	public ProductAnimal newAnimal() {
		System.out.println("开始产马！");
		return new AnimalHorse();
	}

	public ProductPlant newPlant() {
		System.out.println("开始产水果！");
		return new PlantFruitage();
	}
}
