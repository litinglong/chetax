package com.silva.chetax.demo.design.patterns.creational.builder;

/**
 * (3) 具体建造者：实现了抽象建造者接口。
 * @author Administrator
 *
 */
public class ConcreteBuilder extends Builder {
    public void buildPartA() {
        product.setPartA("建造 PartA");
    }
    public void buildPartB() {
        product.setPartB("建造 PartB");
    }
    public void buildPartC() {
        product.setPartC("建造 PartC");
    }
}
