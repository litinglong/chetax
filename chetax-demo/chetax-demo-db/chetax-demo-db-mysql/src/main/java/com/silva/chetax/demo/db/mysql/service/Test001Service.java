package com.silva.chetax.demo.db.mysql.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Test001Service {

	/**
	 * 事务传播详解
	 * https://blog.csdn.net/yanxin1213/article/details/100582643
	 */
//	@Transactional(propagation=Propagation.REQUIRED)
	public void test() {
		String s = sqlSessionFactory.getConfiguration().getMappedStatement("com.silva.chetax.demo.db.mysql.mapper.BookLendRecordMapper.TBookLendRecordRM").getBoundSql(new HashMap()).getSql();
	System.out.println(s);
	}
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
}
