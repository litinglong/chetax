package com.ltl.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;

/*https://blog.csdn.net/noaman_wgs/article/details/103328793
 * Spring Cloud Alibaba

若您是通过 Spring Cloud Alibaba 接入的 Sentinel，则无需额外进行配置即可使用 @SentinelResource 注解。

Spring AOP

若您的应用使用了 Spring AOP（无论是 Spring Boot 还是传统 Spring 应用），您需要通过配置的方式将 SentinelResourceAspect 注册为一个 Spring Bean
 * 限流
*/
@Configuration
public class SentinelAspectConfiguration {

	@Bean
	public SentinelResourceAspect sentinelResourceAspect() {
		return new SentinelResourceAspect();
	}
}
