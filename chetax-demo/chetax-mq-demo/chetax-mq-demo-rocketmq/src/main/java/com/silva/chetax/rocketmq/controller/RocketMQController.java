package com.silva.chetax.rocketmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.silva.chetax.rocketmq.beans.RocketMQSender;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/*http://localhost:8080/swagger-ui.html*/
@Api(value = "RocketMQController", description = "RocketMQController")
@RestController
public class RocketMQController {
    @Autowired
    RocketMQSender rocketMQSender;

    @ApiOperation(value = "send", httpMethod = "GET")
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String send(String msg) {
    	rocketMQSender.send(msg);
        return "字符串消息发送成功!";
    }
    
    @ApiOperation(value = "sendWithTags", httpMethod = "GET")
    @RequestMapping(value = "/sendWithTags", method = RequestMethod.GET)
    public String sendWithTags(String msg) {
    	rocketMQSender.sendWithTags(msg, "tagStr");
        return "带tag字符串消息发送成功!";
    }
    
    @ApiOperation(value = "sendObject", httpMethod = "GET")
    @RequestMapping(value = "/sendObject", method = RequestMethod.GET)
    public String sendObject(int index) {
    	rocketMQSender.sendObject(new Foo(index, "foo"), "tagObj");
        return "Object对象消息发送成功!";
    }
}
