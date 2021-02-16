package com.silva.chetax.spring.life.test.demo.thread;

import java.util.Random;

public class MyThread extends Thread{
	private String name = "MyThread";
	public MyThread(){
	}
	public MyThread(String name){
		this.name = name;
	}
	
	public static void main(String[] args) {
		Thread thread = new MyThread();
		thread.start();
	}
	
	@Override
	public void run() {
		super.run();
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
