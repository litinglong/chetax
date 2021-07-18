package com.silva.chetax.schedule.center.test.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.silva.chetax.schedule.center.test.action.Test1Action;
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
	private Test1Action test1Action;
	
	@RequestMapping("/reset")
	public Object reset() {
		return test1Action.reset(null);
	}
	
	@RequestMapping("/change")
	public Object change(@RequestParam("p") String p) {
		return test1Action.change(p);
	}
	
	
	
//	@RequestMapping("/test1")
//	public String test1() {
//		return iTstTest2Service.updateSomething1()+"";
//	}
	
//	@RequestMapping("/test2")
//	public String test2() {
//		return iTstTest2Service.updateSomething2()+"";
//	}
}
