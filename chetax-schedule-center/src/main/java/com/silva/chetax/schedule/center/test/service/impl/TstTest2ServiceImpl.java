package com.silva.chetax.schedule.center.test.service.impl;

import com.silva.chetax.schedule.center.test.entity.TstTest1;
import com.silva.chetax.schedule.center.test.mapper.TstTest1Mapper;
import com.silva.chetax.schedule.center.test.service.ITstTest1Service;
import com.silva.chetax.schedule.center.test.service.ITstTest2Service;
import com.silva.chetax.schedule.center.test.service.Test1Manager;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author litinglong
 * @since 2021-07-12
 */
@Service
public class TstTest2ServiceImpl implements ITstTest2Service {
	@Autowired
	private ITstTest1Service iTstTest1Service;
	
	@Override
	@Transactional
	public int updateSomething1() {
		int x = iTstTest1Service.updateSomething();
		int a = 1;
		int b = 0;
		int c =a/b;
		return x;
	}
	
	@Override
	@Transactional
	public int updateSomething2() {
		Test1Manager test1Manager = new Test1Manager(iTstTest1Service);
		int x = test1Manager.execute();
		int a = 1;
		int b = 0;
		int c =a/b;
		return x;
	}
}
