package com.silva.chetax.demo.spring.batch.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("IndexCtl")
public class IndexCtl {

    
    @GetMapping("/echo/{message}")
    public String echo(@PathVariable(value="message") String message) {
    	log.info(message);
        return message;
    }
}

