package com.silva.chetax.demo.design.patterns.creational.singleton;

/**
 * type:懒汉式，线程安全
 * <p>是否 Lazy 初始化：是
 * <p>是否多线程安全：是
 * <p>实现难度：易
 * <p>描述：这种方式具备很好的 lazy loading，能够在多线程中很好的工作，但是，效率很低，99% 情况下不需要同步。
 * <p>优点：第一次调用才初始化，避免内存浪费。
 * <p>缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率。
 * <p>getInstance() 的性能对应用程序不是很关键（该方法使用不太频繁）。
 * 
 * @author litinglong
 *
 */
public class ConcurrentLazySingleton {
	private static ConcurrentLazySingleton instance;

	private ConcurrentLazySingleton() {
	}

	public static synchronized ConcurrentLazySingleton getInstance() {
		if (instance == null) {
			instance = new ConcurrentLazySingleton();
		}
		return instance;
	}
	
	public void showMessage() {
		System.out.println("ConcurrentLazySingleton!");
	}
}
