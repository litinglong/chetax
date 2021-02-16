package com.silva.chetax.spring.life.test.demo.thread;

import java.util.Random;

public class MyRunnable implements Runnable{
	private String name = "MyRunnable";
	public MyRunnable(){
	}
	public MyRunnable(String name){
		this.name = name;
	}
	
	public static void main(String[] args) {
		Runnable runnable = new MyRunnable();
		Thread t = new Thread(runnable);
        t.start();
	}
	
	@Override
	public void run() {
		System.out.println(this.name + ":线程开始");
		Random r = new Random();
		int second = r.nextInt(10);
		System.out.println(this.name + ":休眠秒数:" + second);
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(this.name + ":线程结束");
	}
}
