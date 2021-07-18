package com.silva.chetax.schedule.center.test.manager;

import org.slf4j.Logger;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.silva.chetax.schedule.center.common.exception.MyRuntimeException;
import com.silva.chetax.schedule.center.test.action.Test1Action;
import com.silva.chetax.schedule.center.test.entity.TstTest1;

public class Test1ActionManager {
	Test1Action test1Action;
	Logger log;
	Object in;
	
	public Test1ActionManager(Test1Action test1Action, Logger log, Object in){
		this.test1Action = test1Action;
		this.log = log;
		this.in = in;
	}
	
	public void execute() {
		TstTest1 test1 = test1Action.test1Service.getById(1);
		test1.setCode("1");
		test1Action.test1Service.updateById(test1);
		if(StringUtils.isNotBlank((String)in)&&!"2".equals((String)in)) {
			throw new MyRuntimeException((String)in,(String)in + "：msg");
		}
		TstTest1 test2 = test1Action.test1Service.getById(2);
		test2.setCode("1");
		test1Action.test1Service.updateById(test2);
		int a = 1;
		int b = 0;
		int c = a/b;
		if(StringUtils.isNotBlank((String)in)) {
			throw new MyRuntimeException((String)in,(String)in + "：msg");
		}
	}
}
