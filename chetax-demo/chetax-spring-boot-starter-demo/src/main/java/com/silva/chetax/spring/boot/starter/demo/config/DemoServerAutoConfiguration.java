package com.silva.chetax.spring.boot.starter.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.silva.chetax.spring.boot.starter.demo.properties.DemoServerProperties;
import com.silva.chetax.spring.boot.starter.demo.service.DemoServiceImpl;

@Configuration
@ConditionalOnClass(DemoServiceImpl.class)
@EnableConfigurationProperties({ DemoServerProperties.class})
public class DemoServerAutoConfiguration {
	
	@Autowired
	private DemoServerProperties demoServerProperties;
	
	@Bean
	public DemoServiceImpl getDemoService(){
		DemoServiceImpl demoService = new DemoServiceImpl(demoServerProperties.getEnable(), demoServerProperties.getTimes(), demoServerProperties.getDivide(), demoServerProperties.getName());
		demoService.start();
		return demoService;
	}
}
