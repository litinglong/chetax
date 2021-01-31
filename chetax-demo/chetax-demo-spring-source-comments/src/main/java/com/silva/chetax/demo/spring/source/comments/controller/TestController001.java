package com.silva.chetax.demo.spring.source.comments.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("testController001")
public class TestController001 {
	
	@GetMapping("getTest001")
	public String getTest001() {
		return new Date().toString();
	}
}
