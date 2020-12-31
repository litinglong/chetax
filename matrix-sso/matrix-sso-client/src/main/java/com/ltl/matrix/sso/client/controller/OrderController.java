package com.ltl.matrix.sso.client.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * create by caihao on 2020/9/25
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	Logger logger = LoggerFactory.getLogger(OrderController.class);
    @GetMapping("/list")
    @ResponseBody
    public String getOrderList(HttpServletRequest request) {
    	logger.info("client1的/list中sessionId：{}", request.getSession().getId());
        return "getOrderList success";
    }

}
