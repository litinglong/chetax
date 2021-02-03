package com.silva.chetax.demo.design.patterns.creational.factory.abstracts;

import com.silva.chetax.demo.design.patterns.creational.factory.abstracts.impl.sg.SGfarmFactory;
import com.silva.chetax.demo.design.patterns.creational.factory.abstracts.impl.sr.SRfarmFactory;

/**
 * 与非抽象工厂的区别是：抽象工厂是定义一些列产品的生产动作
 * @author Administrator
 *
 */
public interface FarmFactory {
    public ProductAnimal newAnimal();
    public ProductPlant newPlant();
    
    public static FarmFactory createFactory(String name) {
        if (name.equalsIgnoreCase("SR")) {
            return new SRfarmFactory();
        } else if (name.equalsIgnoreCase("SG")) {
            return new SGfarmFactory();
        } else {
            throw new IllegalArgumentException("Invalid factory name");
        }
    }
}
