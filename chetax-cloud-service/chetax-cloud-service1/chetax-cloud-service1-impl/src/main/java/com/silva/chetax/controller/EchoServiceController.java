package com.silva.chetax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EchoServiceController {

    @GetMapping("/echo/{message}")
    public String echo(@PathVariable String message) {
    	try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "[ECHO] : " + message;
    }
    
    @Autowired
    RestTemplate restTemplate;
    
    @GetMapping("/service/{service}")
    public String service(@PathVariable(value="service") String service) {
    	String result = restTemplate.getForObject("http://chetax-service2/"+service+"/333?access_token=f1f77486-54e1-4f98-ac11-7539c3ee5d27", String.class);
        return result;
    }
}

