package com.silva.chetax.demo.design.patterns.creational.factory.abstracts.impl.sg;

import com.silva.chetax.demo.design.patterns.creational.factory.abstracts.ProductAnimal;
import com.silva.chetax.demo.design.patterns.creational.factory.abstracts.FarmFactory;
import com.silva.chetax.demo.design.patterns.creational.factory.abstracts.ProductPlant;

public class SGfarmFactory implements FarmFactory {
	public ProductAnimal newAnimal() {
		System.out.println("开始产牛！");
		return new AnimalCattle();
	}

	public ProductPlant newPlant() {
		System.out.println("开始产蔬菜！");
		return new PlantVegetables();
	}
}
