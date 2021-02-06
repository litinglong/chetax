package com.silva.chetax.demo.spring.source.comments.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.silva.chetax.demo.spring.source.comments.entity.in.EntityReq;

@RestController // 返回json字符串的数据，直接可以编写RESTFul的接口;
@RequestMapping("testController001")
public class TestController001 {
	
	@Value("${my.test}") // 可以注入properties里面的配置项
	private String name1;
	
	@GetMapping("getTest001")
	public String getTest001() {
		return new Date().toString();
	}
	
	@GetMapping("test001ControllerAdvice")
	public String test001ControllerAdvice(Model model) {
        Map<String, Object> map = model.asMap();
        System.out.println(map);
        int i = 1 / 0;
        return new Date().toString();
    }
	
	@GetMapping("test002ControllerAdvice")
	public String test002ControllerAdvice(@ModelAttribute("b") EntityReq req) {
		System.out.println(req);
        return new Date().toString();
    }
}
