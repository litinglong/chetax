package com.silva.chetax.spring.boot.starter.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoServiceImpl implements DemoService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private boolean enable = true;
	private int times = 10;
	private long divide = 5000;
	private String name = "demoServer";

	public DemoServiceImpl(boolean enable, int times, long divide, String name) {
		this.enable = enable;
		this.times = times;
		this.divide = divide;
		this.name = name;
	}

	public void start() {
		if (this.enable) {
			Thread t = new Thread(new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					for (int i = 0; i < times; i++) {
						try {
							logger.info(String.format("线程-%s：总数%d,第%d次", name, times, i));
							Thread.sleep(divide);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			}, name);
			t.start();
		}
	}
}
