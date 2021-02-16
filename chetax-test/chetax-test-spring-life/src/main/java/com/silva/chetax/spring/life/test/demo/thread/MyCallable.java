package com.silva.chetax.spring.life.test.demo.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class MyCallable implements Callable<Integer>{
	private String name = "MyCallable";
	public MyCallable(){
	}
	public MyCallable(String name){
		this.name = name;
	}
	public static void main(String[] args) {
		Callable<Integer> c = new MyCallable();
		FutureTask<Integer> task = new FutureTask<Integer>(c);
		Thread t = new Thread(task);
        t.start();
        Future<Integer> f = task;
        try {
			System.out.println("call返回值>>>" + f.get().toString()); //OPTION + return 抛异常
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Integer call() throws Exception {
		System.out.println(this.name + ":线程开始");
		Random r = new Random();
		int second = r.nextInt(10);
		System.out.println(this.name + ":休眠秒数:" + second);
		Thread.sleep(second * 1000);
		System.out.println(this.name + ":线程结束");
		return r.nextInt();
	}
}
