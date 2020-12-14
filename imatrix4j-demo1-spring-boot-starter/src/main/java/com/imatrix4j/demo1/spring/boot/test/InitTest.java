package com.imatrix4j.demo1.spring.boot.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InitTest {

	
	public static void main(String[] args) {
		InitTest initTest = new InitTest();
		initTest.gotoTest();
	}

	void forTest() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				for (int j2 = 0; j2 < 30; j2++) {
					if(j2>20) {
						break;
					}
				}
				if(j>10) {
					continue;
				}
			}
		}
		int i=0;
		do {
			
		} while (i++<10);
		
		while (i++<100) {
			
		}
		
		List list =new ArrayList() {{add(1);add(1);add(1);}};
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
	void gotoTest() {
		gotoPoint1:
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			if (i>5)
			break gotoPoint1;
		}
	System.out.println(100);
	}
	
	void switchCaseTest() {
		// 条件只能是byte char short int类型
		// default位置不固定，如果所有的case都不符合条件则执行default
		// 如果执行了某个case或者default后不break，则其后的所有case会依次全部执行一遍直到遇到某个break
		// 从语法上来讲可以没有default，如果有则只能有一个default
		int i = 1, j = 0;  
		switch (i) {  
		case 2:  
			System.out.println("up1");
		    j += 6;  
		case 4:  
			System.out.println("up2");
		    j += 1; 
		case 3:  
			System.out.println("up3");
		    j += 1;
		default: 
			System.out.println("default");
		    j += 2; 
		case 0:  
			System.out.println("down1");
		    j += 4; 
		    break;
		case 5:  
			System.out.println("down2");
		    j += 4;
		} 
		System.out.println(j);
	}
	
	void initBeanTest() {
		// 子类实例化的流程测试
		// 测试情景：
		// 【父类】存在多个【静态】语句块， 多个【非静态】语句块
		// 【子类】存在多个【静态】语句块， 多个【非静态】语句块
		// 测试结果：
		// 1.按顺序调用【子类】的全部 【静态】 语句块
		// 2.按顺序调用【父类】的全部 【非静态】 语句块
		// 3.调用【父类】的【构造器】（子类未显示调用某特定父类构造器，则调用默认【无参构造器】）
		// 4.按顺序调用【子类】的全部 【非静态】 语句块
		// 5.调用【子类】的 【构造器】
		// 每个继承的层级，必会且唯一调用一次该层的构造器（不论隐式调用，还是显示调用），静态语句块只能调用子类中的
		InitTestChild childBean = new InitTestChild("childBean");
	}

	void compareTest() {
		Integer a = -129, b = -129, c = 128, d = 128;
		Integer e = 100, f = new Integer(100);

		System.out.println(a == b);
		System.out.println(a.equals(b));
		System.out.println(c == d);
		System.out.println(c.equals(d));
		System.out.println(e == f);
		System.out.println(e.equals(f));
	}

	void nativeArrayTest() {
		int[][] x1 = new int[][] {};
		int[][] x2 = new int[][] { { 1, 3 }, { 2 } };
		int[][] x3 = new int[2][];
		int[][] x4 = new int[2][2];
	}

	void BigDecimalTest() {
		BigDecimal bd = new BigDecimal(0);
		System.out.println(bd.setScale(2, BigDecimal.ROUND_HALF_DOWN));
	}

	void ClassLoaderTest() {
		// 扩展类加载器Main
		ClassLoader classLoader = InitTest.class.getClassLoader();
		// 表示当前线程的类加载器——应用程序类加载器
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		// —启动类加载器
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
	}

}
