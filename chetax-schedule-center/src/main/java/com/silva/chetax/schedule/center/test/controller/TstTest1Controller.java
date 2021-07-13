package com.silva.chetax.schedule.center.test.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.silva.chetax.schedule.center.test.service.ITstTest1Service;
import com.silva.chetax.schedule.center.test.service.ITstTest2Service;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author litinglong
 * @since 2021-07-12
 */
@RestController
@RequestMapping("/test/TstTest1Controller")
public class TstTest1Controller {
	@Autowired
	private ITstTest1Service iTstTest1Service;
	
	@Autowired
	private ITstTest2Service iTstTest2Service;
	
	@RequestMapping("/test")
	public String test() {
		return iTstTest1Service.updateSomething()+"";
	}
	
	@RequestMapping("/test1")
	public String test1() {
		return iTstTest2Service.updateSomething1()+"";
	}
	
	@RequestMapping("/test2")
	public String test2() {
		return iTstTest2Service.updateSomething2()+"";
	}
}
