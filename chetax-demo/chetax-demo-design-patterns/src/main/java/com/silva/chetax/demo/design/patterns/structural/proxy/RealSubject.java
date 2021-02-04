package com.silva.chetax.demo.design.patterns.structural.proxy;

public class RealSubject implements Subject {
    public void request() {
        System.out.println("访问真实主题方法...");
    }
}
