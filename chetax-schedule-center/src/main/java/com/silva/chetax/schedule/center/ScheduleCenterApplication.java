package com.silva.chetax.schedule.center;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableScheduling
//@EnableDiscoveryClient
//@EnableFeignClients
@MapperScan("com.silva.chetax.schedule.center.*.mapper")
@SpringBootApplication
//@EnableAspectJAutoProxy(exposeProxy = true)
public class ScheduleCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleCenterApplication.class, args);
	}

}
