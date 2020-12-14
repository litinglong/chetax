package com.imatrix4j.demo1.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "demo1.pro")
public class DemoServerProperties {
    
    private int times=10;
    private long divide=5000; 
    private String name="demoServer";
    
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public long getDivide() {
		return divide;
	}
	public void setDivide(long divide) {
		this.divide = divide;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
}
