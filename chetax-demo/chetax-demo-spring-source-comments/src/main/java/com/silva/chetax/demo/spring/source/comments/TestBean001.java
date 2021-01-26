package com.silva.chetax.demo.spring.source.comments;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class TestBean001 {
	@Value("${my.test}")
	private String name1;
	
	private String text;
	
	@Autowired
	TestBean002 testBean002;
	
	@PostConstruct 
	void testPostConstruct(){
		
	}
	
	
	@PreDestroy
	void testPreDestroy(){
		
	}
}
