package com.silva.chetax.spring.life.test.demo.spade;

import java.util.List;
import java.util.Map;

public class PrintTask implements Runnable{

	private List<Map<String,String>> list;
	
	PrintTask(List<Map<String, String>> list) {
		this.list = list;
	}
	@Override
	public void run() {
		while (true) {
			System.out.println(list.size());
//			System.out.println(list);
			try {
				Thread.sleep(15*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
