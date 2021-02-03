package com.silva.chetax.demo.design.patterns.behavior.chain.handler;

import java.math.BigDecimal;

import com.silva.chetax.demo.design.patterns.behavior.chain.Handler;
import com.silva.chetax.demo.design.patterns.behavior.chain.Request;

public class DirectorHandler implements Handler {
	public Boolean process(Request request) {
		System.out.println("DirectorHandler process");
		// 如果超过1000元，处理不了，交下一个处理:
		if (request.getAmount().compareTo(BigDecimal.valueOf(1000)) > 0) {
			return null;
		}
		// 对Bob有偏见:
		return !request.getName().equalsIgnoreCase("John");
	}
}
