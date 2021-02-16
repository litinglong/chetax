package com.silva.chetax.spring.life.test.demo.thread.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.silva.chetax.spring.life.test.demo.thread.MyCallable;
import com.silva.chetax.spring.life.test.demo.thread.MyRunnable;
import com.silva.chetax.spring.life.test.demo.thread.MyThread;

public class ScheduledThreadPoolTest {
	public static void main(String[] args) {
		int taskSize=5;
        ExecutorService pool = Executors.newScheduledThreadPool(taskSize);
        Callable<Integer> callable = new MyCallable();
        Runnable runnable = new MyRunnable();
        Thread thread = new MyThread();
        
        // 执行任务并获取Future对象
        Future<Integer> f = pool.submit(callable);
        pool.submit(runnable);
        pool.submit(thread);
        // 关闭线程池
        pool.shutdown();
        // 获取所有并发任务的运行结果
        // 从Future对象上获取任务的返回值，并输出到控制台
        try {
			System.out.println("call返回值>>>" + f.get().toString());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //OPTION + return 抛异常
	}
}
