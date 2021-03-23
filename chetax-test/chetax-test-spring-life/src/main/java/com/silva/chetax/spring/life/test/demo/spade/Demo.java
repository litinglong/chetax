package com.silva.chetax.spring.life.test.demo.spade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {

	public static void main(String[] args) {
		ConcurrentLinkedQueue<String> queue= new ConcurrentLinkedQueue<String>();
		Set<String> set= new HashSet<String>(12000);
		List<Map<String,String>> list= new ArrayList<Map<String,String>>(12000);
		add(queue,set,"http://www.baidu.com");
		add(queue,set,"https://www.csdn.net");
		add(queue,set,"https://www.runoob.com/");
		add(queue,set,"https://blog.gitee.com/");
		add(queue,set,"https://blink.csdn.net/details/1183375");
		
		
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		executorService.submit(new RequestTask(queue,set,list));
		executorService.submit(new RequestTask(queue,set,list));
		executorService.submit(new RequestTask(queue,set,list));
		executorService.submit(new RequestTask(queue,set,list));
		executorService.submit(new RequestTask(queue,set,list));
		executorService.submit(new RequestTask(queue,set,list));
		executorService.submit(new RequestTask(queue,set,list));
		executorService.submit(new RequestTask(queue,set,list));
		executorService.submit(new RequestTask(queue,set,list));
		executorService.submit(new RequestTask(queue,set,list));
		
		executorService.submit(new RequestTask(queue,set,list));
		executorService.submit(new RequestTask(queue,set,list));
		executorService.submit(new RequestTask(queue,set,list));
		executorService.submit(new RequestTask(queue,set,list));
		executorService.submit(new RequestTask(queue,set,list));
		executorService.submit(new RequestTask(queue,set,list));
		executorService.submit(new RequestTask(queue,set,list));
		executorService.submit(new RequestTask(queue,set,list));
		executorService.submit(new RequestTask(queue,set,list));
		executorService.submit(new PrintTask(list));
		executorService.shutdown();
	}
	
	private static void add(ConcurrentLinkedQueue<String> queue,Set<String> set,String uri) {
		queue.add(uri);
		set.add(uri);
	}

}
