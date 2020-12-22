package com.ltl.matrix.spring.life.test;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.ltl.matrix.spring.life.test.demo.event.DemoEvent;
import com.ltl.matrix.spring.life.test.demo.event.DemoPublisher;

@SpringBootApplication
public class SpringLifeTestApplication {
	@Autowired
	static DemoPublisher demoPublisher;
	
	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext configurableApplicationContext =SpringApplication.run(SpringLifeTestApplication.class, args);
//		configurableApplicationContext.registerShutdownHook();
//		demoPublisher.publish(1L, "成功了！");
		CountDownLatch count = new CountDownLatch(1);
		configurableApplicationContext.publishEvent(new DemoEvent(count, 1L, "成功了！"));
		count.await();
	}

}
