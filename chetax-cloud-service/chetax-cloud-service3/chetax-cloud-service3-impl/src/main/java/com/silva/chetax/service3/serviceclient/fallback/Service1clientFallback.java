package com.silva.chetax.service3.serviceclient.fallback;

import com.silva.chetax.service3.serviceclient.Service1client;

public class Service1clientFallback implements Service1client{

	@Override
	public String echo(String message) {
		// TODO Auto-generated method stub
		return "已进行降级相关处理";
	}

}
