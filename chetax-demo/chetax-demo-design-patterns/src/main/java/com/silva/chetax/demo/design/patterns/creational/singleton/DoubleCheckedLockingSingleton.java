package com.silva.chetax.demo.design.patterns.creational.singleton;

/**
 * type:双检锁/双重校验锁（DCL，即 double-checked locking）
 * <p> JDK 版本：JDK1.5 起
 * <p> 是否 Lazy 初始化：是
 * <p> 是否多线程安全：是
 * <p> 实现难度：较复杂
 * <p> 描述：这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
 * <p> getInstance() 的性能对应用程序很关键。
 * 
 * @author litinglong
 *
 */
public class DoubleCheckedLockingSingleton {
	private volatile static DoubleCheckedLockingSingleton singleton;

	private DoubleCheckedLockingSingleton() {
	}

	public static DoubleCheckedLockingSingleton getInstance() {
		if (singleton == null) {
			synchronized (DoubleCheckedLockingSingleton.class) {
				if (singleton == null) {
					singleton = new DoubleCheckedLockingSingleton();
				}
			}
		}
		return singleton;
	}
	
	public void showMessage() {
		System.out.println("DoubleCheckedLockingSingleton!");
	}
}
