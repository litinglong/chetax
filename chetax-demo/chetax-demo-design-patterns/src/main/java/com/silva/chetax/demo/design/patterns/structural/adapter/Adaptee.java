package com.silva.chetax.demo.design.patterns.structural.adapter;

/**
 * 适配者接口
 * @author Administrator
 *
 */
public class Adaptee {
    public void specificRequest()
    {       
        System.out.println("适配者中的业务代码被调用！");
    }
}
