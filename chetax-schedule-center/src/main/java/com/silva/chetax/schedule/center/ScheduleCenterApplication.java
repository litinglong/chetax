package com.silva.chetax.schedule.center;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableScheduling
//@EnableDiscoveryClient
//@EnableFeignClients
@MapperScan("com.silva.chetax.schedule.center.sys.mapper")
@SpringBootApplication
public class ScheduleCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleCenterApplication.class, args);
	}

}
