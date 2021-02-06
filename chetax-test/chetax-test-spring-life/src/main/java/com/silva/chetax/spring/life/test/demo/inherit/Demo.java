package com.silva.chetax.spring.life.test.demo.inherit;

/**
 * 在通过子类的构造函数创建子类对象时，在子类构造函数中的第一个赋值语句前须先(隐式或显示)调用唯一一次父类的一个构造函数（在其所有的构造函数中，不论有参或无参）
 * @author litinglong
 *
 */
public class Demo {
	public static void main(String[] args) {
		System.out.println("无参构造：");
		non();
		System.out.println();
		System.out.println("有参构造：");
		args();
	}
	
	public static void non() {
		AbstractLv1 abstractLv1 = new AbstractLv1() {};
		
		System.out.println("---------------------------");
		Lv2 lv2 = new Lv2();
		
		System.out.println("---------------------------");
		Lv3 lv3 = new Lv3();
		
		System.out.println("---------------------------");
		Lv4 lv4 = new Lv4();
	}
	
	public static void args() {
		
		AbstractLv1 abstractLv1 = new AbstractLv1("") {};
		
		System.out.println("---------------------------");
		Lv2 lv2 = new Lv2("");
		
		System.out.println("---------------------------");
		Lv3 lv3 = new Lv3("");
		
		System.out.println("---------------------------");
		Lv4 lv4 = new Lv4("");
	}
	
}
