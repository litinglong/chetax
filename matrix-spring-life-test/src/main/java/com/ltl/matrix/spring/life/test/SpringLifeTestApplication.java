package com.ltl.matrix.spring.life.test;

import java.util.concurrent.CountDownLatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringLifeTestApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext configurableApplicationContext =SpringApplication.run(SpringLifeTestApplication.class, args);
		configurableApplicationContext.registerShutdownHook();
//		CountDownLatch count = new CountDownLatch(1);
//		count.await();
	}

}
