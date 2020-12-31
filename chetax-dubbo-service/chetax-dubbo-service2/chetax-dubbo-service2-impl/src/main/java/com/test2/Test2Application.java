package com.test2;

import java.util.concurrent.CountDownLatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath*:applicationContext.xml")
public class Test2Application {
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Test2Application.class, args);
		CountDownLatch latch = new CountDownLatch(1);
		latch.await();
	}
}
