package com.silva.chetax.demo.spring.source.comments;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
// 指定文件地址。提供了一种方便的、声明性的机制，用于向Spring的环境添加PropertySource。与@configuration类一起使用。
@PropertySource(value = {"classpath:user.properties"})
@ConfigurationProperties(prefix = "user")
public class TestBean003 {
	private Integer id;
    private String lastName;
    private Integer age;
    private Date birthday;
    private List<String> colorList;
    private Map<String, String> cityMap;


}
