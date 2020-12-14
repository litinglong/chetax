package com.imatrix4j.demo1.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(DemoServiceImpl.class)
@EnableConfigurationProperties({ DemoServerProperties.class})
public class DemoServerAutoConfiguration {
	
	@Autowired
	private DemoServerProperties demoServerProperties;
	
	@Bean
	public DemoServiceImpl getDemoService(){
		DemoServiceImpl demoService = new DemoServiceImpl(demoServerProperties.getTimes(), demoServerProperties.getDivide(), demoServerProperties.getName());
		
		demoService.start();
		
		return demoService;
	}
}
