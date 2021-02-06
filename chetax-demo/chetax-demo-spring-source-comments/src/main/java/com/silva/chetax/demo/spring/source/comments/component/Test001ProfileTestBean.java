package com.silva.chetax.demo.spring.source.comments.component;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component // 表示一个带注释的类是一个“组件”，成为Spring管理的Bean。当使用基于注解的配置和类路径扫描时，这些类被视为自动检测的候选对象。同时@Component还是一个元注解。
@Data
@Profile("test") // 也可以注解在@Configeration的@Bean的方法上，根据生效的profile的不同，决定要不要实例化成为spring的组件
public class Test001ProfileTestBean {
	private String text;
	
}
