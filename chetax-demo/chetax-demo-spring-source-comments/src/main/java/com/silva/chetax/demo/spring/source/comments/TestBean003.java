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
