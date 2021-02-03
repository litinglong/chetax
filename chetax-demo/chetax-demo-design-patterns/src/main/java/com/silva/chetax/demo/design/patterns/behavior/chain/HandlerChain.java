package com.silva.chetax.demo.design.patterns.behavior.chain;

import java.util.ArrayList;
import java.util.List;

public class HandlerChain {
	// 持有所有Handler:
	private List<Handler> handlers = new ArrayList<>();

	public void addHandler(Handler handler) {
		this.handlers.add(handler);
	}

	public boolean process(Request request) {
		// 依次调用每个Handler:
		for (Handler handler : handlers) {
			Boolean r = handler.process(request);
			if (r != null) {
				// 如果返回TRUE或FALSE，处理结束:
				System.out.println(
						request.getName() + " " + (r ? "Approved by " : "Denied by ") + handler.getClass().getSimpleName());
				return r;
			}
		}
		//如果全部handler执行完都没有返回，则表示请求参数超出处理能力，则抛出异常
		throw new RuntimeException("Could not handle request: " + request);
	}
}
