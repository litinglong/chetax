package com.ltl.matrix.spring.life.test.demo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@TestAnnotation(valuex="444")
public class DemoClass implements InitializingBean,DisposableBean,BeanNameAware,BeanFactoryAware{
	public static final String CLZZ_NAME="DemoClass";
	private String name;
	public DemoClass() {
		log.info(CLZZ_NAME+":【构造器】调用DemoClass的构造器实例化");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		log.info(CLZZ_NAME+":setName");
		this.name = name;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info(CLZZ_NAME+":InitializingBean:afterPropertiesSet");
		
	}
	
	@Override
	public void destroy() throws Exception {
		log.info(CLZZ_NAME+":DisposableBean:destroy");
	}
	@PostConstruct
	void postConstruct(){
		log.info(CLZZ_NAME+":@PostConstruct");
	}

	@PreDestroy
	void preDestroy(){
		log.info(CLZZ_NAME+":@PreDestroy");
	}
	
	@Override
	public void setBeanName(String name) {
		log.info(CLZZ_NAME+":BeanNameAware:setBeanName:"+name);
		
	}
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		log.info(CLZZ_NAME+":BeanFactoryAware:setBeanFactory");
	}

}
