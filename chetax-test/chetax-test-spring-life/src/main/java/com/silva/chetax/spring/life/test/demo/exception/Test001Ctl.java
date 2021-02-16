package com.silva.chetax.spring.life.test.demo.exception;

public class Test001Ctl {
	private final TestService s = new TestService();
	
	public TestService getS() {
		return s;
	}
	
	// 如果被调用的方法抛出运行时异常，调用方不必须处理目标异常
	void testUncheckedException1() {
		getS().testUncheckedException1();
	}
	
	void testUncheckedException2(){
		getS().testUncheckedException2();
	}
	
	// 如果被调用的方法抛出受检查异常，调用方必须处理目标异常（throws或者catch）
	void testCheckedException1() throws CheckedException{
		getS().testCheckedException();
	}
	
	void testCheckedException2(){
		try {
			getS().testCheckedException();
		} catch (CheckedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
