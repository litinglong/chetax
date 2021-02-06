package com.silva.chetax.demo.spring.source.comments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.silva.chetax.demo.spring.source.comments.service.Test001Service;
import com.silva.chetax.demo.spring.source.comments.service.Test002Service;

/**
 * @author litinglong
 *
 */
@Controller // 控制层，里面有多个连接
@RequestMapping("test003Ctl")
public class Test003Ctl {
	@Autowired
	Test001Service test001Service;
	
	@Autowired
	@Qualifier("test002aServiceImpl")
	Test002Service test002aService;
	
	@Autowired // 自动注入依赖
	@Qualifier("test002bServiceImpl") // 如果一个接口有多个实现，那么注入时候加上唯一标示
	Test002Service test002bService;
}
