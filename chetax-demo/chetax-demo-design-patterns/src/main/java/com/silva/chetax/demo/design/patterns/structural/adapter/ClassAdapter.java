package com.silva.chetax.demo.design.patterns.structural.adapter;

/**
 * 类适配器类
 * @author Administrator
 *
 */
public class ClassAdapter extends Adaptee implements Target
{
    public void request()
    {
        specificRequest();
    }
}
