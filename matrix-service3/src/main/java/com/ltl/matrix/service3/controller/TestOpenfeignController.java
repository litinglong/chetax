package com.ltl.matrix.service3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ltl.matrix.service3.serviceclient.Service1client;

@RestController
public class TestOpenfeignController {

    Logger logger = LoggerFactory.getLogger(TestOpenfeignController.class);
    
    @Autowired
    Service1client Service1client;
    
    @GetMapping("/echo/{message}")
    public String echo(@PathVariable(value="message") String message) {
        return Service1client.echo(message);
    }
}

