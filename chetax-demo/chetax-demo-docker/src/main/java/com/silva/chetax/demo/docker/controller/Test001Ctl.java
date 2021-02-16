package com.silva.chetax.demo.docker.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litinglong
 *
 */
@RestController
@RequestMapping("test001Ctl")
public class Test001Ctl {
	
	@RequestMapping("test001")
	public String test001() {
		return (new Date()).toString();
	}
	
}
