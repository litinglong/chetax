package com.silva.chetax.schedule.center.test.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import com.silva.chetax.schedule.center.common.exception.MyRuntimeException;
import com.silva.chetax.schedule.center.test.entity.TstTest1;
import com.silva.chetax.schedule.center.test.manager.Test1ActionManager;
import com.silva.chetax.schedule.center.test.service.ITstTest1Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Test1Action {
	@Autowired
	public ITstTest1Service test1Service;
	
	@Autowired
	public TransactionTemplate transactionTemplate;
	
	public Object reset(Object in){
		TstTest1 test1 = test1Service.getById(1);
		test1.setCode("999999");
		boolean tag1 = test1Service.updateById(test1);
		TstTest1 test2 = test1Service.getById(2);
		test2.setCode("999999");
		boolean tag2 = test1Service.updateById(test2);
		return String.format("%s:%s", tag1, tag2);
	}
	
	public Object change(Object in){
		PlatformTransactionManager  manager = transactionTemplate.getTransactionManager();
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);//新发起一个事务
	    TransactionStatus status = manager.getTransaction(def);
		Object resultObject = null;
		try {
			Test1ActionManager test1Manager = new Test1ActionManager(this, log, in);
			test1Manager.execute();
		} 
		catch (MyRuntimeException ex) {
			log.error("MyRuntimeException");
			if("2".equals(ex.getErrorCode()))
				manager.commit(status);
			else {
				manager.rollback(status);
			}
			throw ex;
		}
		catch (Exception ex) {
			log.error("Exception");
			manager.rollback(status);
			throw ex;
		}
		return resultObject;
	}
}
