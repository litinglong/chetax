package com.silva.chetax.demo.design.patterns.creational.factory.abstracts.impl.sg;

import com.silva.chetax.demo.design.patterns.creational.factory.abstracts.ProductPlant;

public class PlantVegetables implements ProductPlant {

	public PlantVegetables() {
		System.out.println("植物：蔬菜");
	}

	public void show() {
		System.out.println("植物：蔬菜：落地");
	}
}
