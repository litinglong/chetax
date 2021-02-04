package com.silva.chetax.demo.design.patterns.behavior.iterator;

/**
 * http://c.biancheng.net/view/1395.html
 * 
 * <p> 1. 迭代器模式的结构
 * <p> 迭代器模式主要包含以下角色。
 * <p> 抽象聚合（Aggregate）角色：定义存储、添加、删除聚合对象以及创建迭代器对象的接口。
 * <p> 具体聚合（ConcreteAggregate）角色：实现抽象聚合类，返回一个具体迭代器的实例。
 * <p> 抽象迭代器（Iterator）角色：定义访问和遍历聚合元素的接口，通常包含 hasNext()、first()、next() 等方法。
 * <p> 具体迭代器（Concretelterator）角色：实现抽象迭代器接口中所定义的方法，完成对聚合对象的遍历，记录遍历的当前位置。
 * 
 * @author Administrator
 *
 */
public class Demo {
	public static void main(String[] args) {
		Aggregate ag = new ConcreteAggregate();
		ag.add("中山大学");
		ag.add("华南理工");
		ag.add("韶关学院");
		System.out.print("聚合的内容有：");
		Iterator it = ag.getIterator();
		while (it.hasNext()) {
			Object ob = it.next();
			System.out.print(ob.toString() + "\t");
		}
		Object ob = it.first();
		System.out.println("\nFirst：" + ob.toString());
	}
}
