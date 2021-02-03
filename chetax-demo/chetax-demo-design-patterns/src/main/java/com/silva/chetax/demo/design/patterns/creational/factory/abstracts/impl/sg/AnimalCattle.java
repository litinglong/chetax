package com.silva.chetax.demo.design.patterns.creational.factory.abstracts.impl.sg;

import com.silva.chetax.demo.design.patterns.creational.factory.abstracts.ProductAnimal;

public class AnimalCattle implements ProductAnimal {
    public AnimalCattle() {
    	System.out.println("动物：牛");
    }
    public void show() {
    	System.out.println("动物：牛：落地");
    }
}
