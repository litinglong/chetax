package com.silva.chetax.kafka.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.silva.chetax.kafka.beans.KafkaSender;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/*http://localhost:8080/swagger-ui.html*/
@Api(value = "eureka-provider", description = "学生查询接口")
@RestController
public class KafkaController {
	@Autowired
	KafkaSender kafkaSender;
	
	@ApiOperation(value = "test1", httpMethod = "POST")
    @PostMapping(value = "/test1", produces = "application/json;charset=UTF-8")
    public void test1(String message, HttpServletRequest request,
                             HttpServletResponse response) {
		kafkaSender.sendToDefaultChannel(message);
		kafkaSender.sendToDefaultChannel(message);
		kafkaSender.sendToDefaultChannel(message);
		kafkaSender.sendToDefaultChannel(message);
    }

    @ApiOperation(value = "test2", httpMethod = "POST")
    @PostMapping(value = "/test2", produces = "application/json;charset=UTF-8")
    public void test2(String message, HttpServletRequest request,
                      HttpServletResponse response) {
    	kafkaSender.sendToAlarmChannel(message);
    }
}
