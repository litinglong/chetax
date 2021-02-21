package com.silva.chetax.demo.docker.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private static final Log log = LogFactory.getLog(HelloController.class);

	@Autowired
	private DiscoveryClient discoveryClient;
	
    /**
     * 探针检查响应类
     * @return
     */
    @RequestMapping("/health")
    public String health() {
        return "health";
    }
    
	@RequestMapping("/")
	public String hello() {
		return "Hello World";
	}

	@RequestMapping("/services")
	public List<String> services() {
		return this.discoveryClient.getServices();
	}

}
