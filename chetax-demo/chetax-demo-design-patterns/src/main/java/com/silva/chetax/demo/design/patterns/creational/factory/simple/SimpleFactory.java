package com.silva.chetax.demo.design.patterns.creational.factory.simple;

public class SimpleFactory {
    public static Product makeProduct(Const kind) {
        switch (kind.getCode()) {
            case 1:
                return new ConcreteProduct1();
            case 2:
                return new ConcreteProduct2();
        }
        return null;
    }
}
