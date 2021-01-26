package com.silva.chetax.demo.spring.source.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class TestBean002 {
	@Value("${my.test}")
	private String name2;
	
	@Autowired
	TestBean001 testBean001;
}
