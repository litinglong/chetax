package com.silva.chetax.demo.db.mysql.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Test001Service {

	/**
	 * 事务传播详解
	 * https://blog.csdn.net/yanxin1213/article/details/100582643
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void test() {
		
	}
}
