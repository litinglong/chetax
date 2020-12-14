package com.ltl.matrix.service2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ltl.matrix.service2.serviceclient.Service1client;

@RestController
public class TestOpenfeignController {

    Logger logger = LoggerFactory.getLogger(TestOpenfeignController.class);
    
    @Autowired
    Service1client Service1client;
    
    @GetMapping("/echo/{message}")
    public String echo(@PathVariable(value="message") String message) {
        return Service1client.echo(message);
    }
    
    @Autowired
    @Qualifier(value="myRestTemplate")
    RestTemplate restTemplate;
    
    @GetMapping("/test333/{message}")
    public String test333(@PathVariable(value="message") String message) {
    	String result = restTemplate.getForObject("http://matrix-service1/echo/333", String.class);
        return result;
    }

}

