package com.imatrix4j.demo1.spring.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoServiceImpl implements DemoService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    private int times=10;
    private long divide=5000; 
    private String name="demoServer";
    
	public DemoServiceImpl(int times, long divide, String name) {
		this.times = times;
		this.divide = divide;
		this.name = name;
	}

	public void start() {
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
