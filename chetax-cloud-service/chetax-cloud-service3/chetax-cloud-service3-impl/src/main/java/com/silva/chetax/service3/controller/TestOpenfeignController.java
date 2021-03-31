package com.silva.chetax.service3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.silva.chetax.service3.serviceclient.Service1client;

@RestController
public class TestOpenfeignController {

    Logger logger = LoggerFactory.getLogger(TestOpenfeignController.class);
    
    @Autowired
    Service1client Service1client;
    
    @GetMapping("/echo/{message}")
    public String echo(@PathVariable(value="message") String message) {
        return Service1client.echo(message);
    }
    
//    @GetMapping("/test/{message}")
//    @SentinelResource(value = "test", blockHandler = "flowException")
//    public String test(@PathVariable(value="message") String message) {
//		try {
//			Thread.sleep(4500);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		return "Hello I'm nacos_provide degrade";
//    }
//    
//    
//    @SuppressWarnings("unused")
//	private String flowException(BlockException e) {
//    	return "降級了";
//    }
}

