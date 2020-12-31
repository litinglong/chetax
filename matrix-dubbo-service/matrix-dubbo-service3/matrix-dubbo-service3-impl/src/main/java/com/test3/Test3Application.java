package com.test3;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class Test3Application {
   public static void main(String[] args) throws InterruptedException {
	   SpringApplication.run(Test3Application.class, args);		
	}
}


