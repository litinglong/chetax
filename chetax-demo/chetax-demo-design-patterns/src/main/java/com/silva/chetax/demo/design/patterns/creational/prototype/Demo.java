package com.silva.chetax.demo.design.patterns.creational.prototype;

/**
 * http://c.biancheng.net/view/1343.html
 * 
 * <p> 原型模式的克隆分为浅克隆和深克隆。
 * <p> 浅克隆：创建一个新对象，新对象的属性和原来对象完全相同，对于非基本类型属性，仍指向原有属性所指向的对象的内存地址。
 * <p> 深克隆：创建一个新对象，属性中引用的其他对象也会被克隆，不再指向原有对象地址。
 * 
 * @author Administrator
 *
 */
public class Demo {
	public static void main(String[] args) throws CloneNotSupportedException {

		PrototypeObject ref1 = new PrototypeObject();
		PrototypeObject obj1 = new PrototypeObject();
		obj1.setRef(ref1);
		PrototypeObject obj2 = (PrototypeObject) obj1.clone();
		System.out.println("obj1==obj2?" + (obj1 == obj2));
		// 浅拷贝，引用对象不会被拷贝
		System.out.println("ref1==obj1.ref?" + (ref1 == obj1.getRef()));
		System.out.println("obj1.ref==obj2.ref?" + (obj1.getRef() == obj2.getRef()));
	}
}
