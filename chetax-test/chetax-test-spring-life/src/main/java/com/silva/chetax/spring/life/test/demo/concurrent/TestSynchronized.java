package com.silva.chetax.spring.life.test.demo.concurrent;

public class TestSynchronized {
	private static int a = 0;
	private static Object obj = new Object();
	public static void main(String[] args) throws InterruptedException {
		final int threadSize = 100000;
		Thread[] ts = new Thread[threadSize];
		for (int i = 0; i < threadSize; i++) {
			ts[i] = new Thread() {
				public void run() {
					synchronized (obj) {
						a++;
					}
				}
			};
		}
		long start = System.currentTimeMillis();
		for (Thread t : ts) {
			t.start();
		}
		for (Thread t : ts) {
			t.join();
		}
		long end = System.currentTimeMillis();
		System.out.println(a);
		System.out.println(end - start);
	}
}
