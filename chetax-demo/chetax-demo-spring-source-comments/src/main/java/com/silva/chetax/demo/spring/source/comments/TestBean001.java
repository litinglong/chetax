package com.silva.chetax.demo.spring.source.comments;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component // 表示一个带注释的类是一个“组件”，成为Spring管理的Bean。当使用基于注解的配置和类路径扫描时，这些类被视为自动检测的候选对象。同时@Component还是一个元注解。
@Data
public class TestBean001 {
	@Value("${my.test}")
	private String name1;
	
	private String text;
	
	@Autowired
	TestBean002 testBean002;
	
	/**
	 * @PostConstruct 标注在方法上，该方法在构造函数执行完成之后执行。
	 */
	@PostConstruct 
	void testPostConstruct(){
		
	}
	
	
	/**
	 * @PreDestroy标注在方法上，该方法在对象销毁之前执行。
	 */
	@PreDestroy
	void testPreDestroy(){
		
	}
}
