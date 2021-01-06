package com.silva.chetax.demo.cache.redis.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.silva.chetax.demo.cache.redis.utils.RedisUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("radisController")
public class RadisController {
	@Autowired
	RedisUtil redisUtil;
	
	@GetMapping("test")
	public String test() {
		String dates = new Date().toString();
		String key = "radisController:test";
		redisUtil.lSet(key, dates, 10*1000);
		List<Object> list = redisUtil.lGet(key, 0, -1);
		for (Object object : list) {
			log.info(object.toString());
		}
		return dates;
	}
	
	@GetMapping("del")
	public String del() {
		String dates = new Date().toString();
		String key = "radisController:test";
		redisUtil.del(key);
		log.info(dates);
		log.warn(dates);
		log.error(dates);
		log.debug(dates);
		log.trace(dates);
		return dates;
	}
}
