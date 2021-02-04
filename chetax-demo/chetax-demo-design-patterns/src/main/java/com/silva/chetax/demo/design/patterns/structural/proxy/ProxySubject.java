package com.silva.chetax.demo.design.patterns.structural.proxy;

public class ProxySubject implements Subject {
    private RealSubject realSubject;
    
    public ProxySubject(RealSubject realSubject) {
		super();
		this.realSubject = realSubject;
	}
    
	public void request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        preRequest();
        realSubject.request();
        postRequest();
    }
	
    public void preRequest() {
        System.out.println("访问真实主题之前的预处理。");
    }
    
    public void postRequest() {
        System.out.println("访问真实主题之后的后续处理。");
    }
}
