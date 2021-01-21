package com.silva.chetax.demo.spring.source.comments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 启动类的几个作用：
 * 
 * A类.由main方法支持
 * 1.作为应用程序的入口，推断环境类别，加载配置文件，创建spring ioc容器，如果是web应用，则创建选定的web容器服务器，并将spring上下文设置到web容器的上下文中去
 * 
 * B类.由@SpringBootApplication中的@SpringBootConfiguration、@EnableAutoConfiguration、@ComponentScan进行支持
 * 1.表示自己本身也是一个配置类，可以用@Bean等注解来配置运行环境（非必须）
 * 2.作为bean扫描的坐标，将扫描范围内的被@Component及其子注解标注的类实例化成为一个bean并注册到spring ioc环境当中
 * 3.标志应用程序是一个自动配置的应用程序，通过该注解的父注解@Import中的AutoConfigurationImportSelector类的selectImports方法来自动加载在META－INF／spring.factories中工厂类型为org.springframework.boot.autoconfigure.EnableAutoConfiguration下的配置类
 * 
 * ps：如果没有springboot的@SpringBootApplication支持，位于依赖的jar包中的配置类并不会自动加载到spring容器中
 * 
 * @author litinglong
 *
 */
@SpringBootApplication
public class SpringSourceCommentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSourceCommentsApplication.class, args);
	}

}
