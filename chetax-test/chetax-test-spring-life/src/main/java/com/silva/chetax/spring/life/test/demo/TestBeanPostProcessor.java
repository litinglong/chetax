package com.silva.chetax.spring.life.test.demo;

import java.lang.annotation.Annotation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestBeanPostProcessor implements BeanFactoryPostProcessor,BeanPostProcessor{
	public static final String CLZZ_NAME="TestBeanPostProcessor";
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		log.info(CLZZ_NAME+"BeanFactoryPostProcessor:postProcessBeanFactory");
		
	}
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		log.info(CLZZ_NAME+"BeanPostProcessor:postProcessBeforeInitialization:"+beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		log.info(CLZZ_NAME+":postProcessAfterInitialization:"+beanName);
		Class<?> clazz;
		try {
			clazz = Class.forName(bean.getClass().getName());
//			TestAnnotation testAnnotation=(TestAnnotation) clazz.getAnnotation(TestAnnotation.class);
			TestAnnotation testAnnotation = bean.getClass().getAnnotation(TestAnnotation.class);
			
			if(testAnnotation!=null) {
				log.info(CLZZ_NAME+":TestAnnotation:"+testAnnotation.valuex());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return bean;
	}


}
