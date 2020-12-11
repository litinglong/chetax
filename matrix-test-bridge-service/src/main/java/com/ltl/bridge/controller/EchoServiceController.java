package com.ltl.bridge.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test4.service.Test4Service;

@RestController
public class EchoServiceController {
	
	@Reference(version = "1.0.0", group = "test", check = false)
    private Test4Service test4Service;
	
    @GetMapping("/echo/{message}")
    public String echo(@PathVariable String message) {
        return "[ECHO] : " + message;
    }
    
    @GetMapping("/test/{name}")
    public String test(@PathVariable String name) {
        return test4Service.sayName(name);
    }
}

