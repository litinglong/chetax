package com.ltl.matrix.sso.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SsoClientController {

    Logger logger = LoggerFactory.getLogger(SsoClientController.class);
    
    @GetMapping("/echo/{message}")
    public String echo(@PathVariable(value="message") String message) {
        return message;
    }
    
    @GetMapping("/page1")
    public String page1() {
        return "/page1";
    }
}

