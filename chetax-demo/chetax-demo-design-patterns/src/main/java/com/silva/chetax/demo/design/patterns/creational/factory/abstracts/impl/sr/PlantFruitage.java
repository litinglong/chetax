package com.silva.chetax.demo.design.patterns.creational.factory.abstracts.impl.sr;

import com.silva.chetax.demo.design.patterns.creational.factory.abstracts.ProductPlant;

public class PlantFruitage implements ProductPlant {

	public PlantFruitage() {
		System.out.println("植物：水果");
	}

	public void show() {
		System.out.println("植物：水果：落地");
	}
}
