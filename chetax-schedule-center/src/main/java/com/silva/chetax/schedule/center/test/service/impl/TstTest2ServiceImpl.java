package com.silva.chetax.schedule.center.test.service.impl;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.silva.chetax.schedule.center.common.exception.MyRuntimeException;
import com.silva.chetax.schedule.center.test.entity.TstTest1;
import com.silva.chetax.schedule.center.test.service.ITstTest1Service;
import com.silva.chetax.schedule.center.test.service.ITstTest2Service;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author litinglong
 * @since 2021-07-12
 */
@Service
@Slf4j
public class TstTest2ServiceImpl implements ITstTest2Service {
	@Autowired
	private ITstTest1Service iTstTest1Service;
	@Autowired 
	PlatformTransactionManager manager;
	@Autowired
	TransactionTemplate transactionTemplate;

//	@Override
//	@Transactional(rollbackFor = Exception.class)
	public int updateSomething1() {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);//新发起一个事务
	    TransactionStatus status = manager.getTransaction(def);
		Object object = null;
		int x = 0;
		try {
			TstTest1 test1 = iTstTest1Service.getById(4);
			test1.setCode("5");
			iTstTest1Service.updateById(test1);
//			manager.commit(status);
			object = status.createSavepoint();
			x = iTstTest1Service.updateSomething();
//			//状态刷新
//			status.flush();
//
//			int a = 1;
//			int b = 0;
//			int c =a/b;
//			return x;
//            //事物提交
//			manager.commit(status);
			throw new MyRuntimeException();
		} 
		catch (MyRuntimeException ex) {
			log.info("RuntimeException");
//			manager.commit(status);
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//			if(object != null) {
				status.rollbackToSavepoint(object);
//				status.releaseSavepoint(object);
//			}
			throw ex;
		}
		catch (Exception ex) {
			log.info("Exception");
			manager.rollback(status);
			throw ex;
		}finally {
			log.info(x+"");
			return x;
		}
	}

//	@Override
//	@Transactional
//	public int updateSomething2() {
//		Test1Manager test1Manager = new Test1Manager(iTstTest1Service);
//		int x = test1Manager.execute();
//		int a = 1;
//		int b = 0;
//		int c = a / b;
//		return x;
//	}
	
	public int updateSomething3() {
		transactionTemplate.executeWithoutResult(new Consumer<TransactionStatus>() {
			
			@Override
			public void accept(TransactionStatus status) {
				// TODO Auto-generated method stub
				try {
					
		        } catch (RuntimeException ex) {
		            status.setRollbackOnly();
		        }
			}
		});
		transactionTemplate.execute(new TransactionCallback<Object>() {

			@Override
			public Object doInTransaction(TransactionStatus status) {
				// TODO Auto-generated method stub
				try {
					
		        } catch (RuntimeException ex) {
		            status.setRollbackOnly();
		        }
				return null;
			}
		});
		return 0;
	}
}
