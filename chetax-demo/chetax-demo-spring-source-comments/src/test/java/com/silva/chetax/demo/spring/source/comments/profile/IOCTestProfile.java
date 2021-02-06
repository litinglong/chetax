package com.silva.chetax.demo.spring.source.comments.profile;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.silva.chetax.demo.spring.source.comments.SpringSourceCommentsApplication;

public class IOCTestProfile {
	//1. 使用命令行动态参数：在虚拟机参数位置加载 -Dspring.profiles.active=test
	//2. 使用代码的方式激活某种环境；
	public void test01() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringSourceCommentsApplication.class);
		//1. 创建一个applicationContext
		//2. 设置需要激活的环境
		applicationContext.getEnvironment().setActiveProfiles("dev","master");
		//3. 注册主配置类
		applicationContext.register(SpringSourceCommentsApplication.class);
		//4. 启动刷新容器
		applicationContext.refresh();
		
		String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
		System.out.println(Arrays.toString(beanNamesForType));
		
		applicationContext.close();
	}
 
 
	public void test02() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringSourceCommentsApplication.class);
		
		String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
		System.out.println(Arrays.toString(beanNamesForType));
		
		applicationContext.close();
	}
}