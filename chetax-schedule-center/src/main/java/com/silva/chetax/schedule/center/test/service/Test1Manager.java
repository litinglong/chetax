package com.silva.chetax.schedule.center.test.service;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

public class Test1Manager {
	private ITstTest1Service iTstTest1Service;
	public Test1Manager(ITstTest1Service iTstTest1Service){
		this.iTstTest1Service = iTstTest1Service;
	}
	
	public int execute() {
		return iTstTest1Service.updateSomething();
	}
}
