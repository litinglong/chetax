package com.silva.chetax.demo.spring.batch.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @Description: 若一个方法一次执行不完下次轮转时则等待改方法执行完后才执行下一次操作
 * @date 2014年4月24日 下午5:05:47
 */
@DisallowConcurrentExecution
@Slf4j
public class JobFactoryDisallowConcurrentExecution implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SysJob sysJob = (SysJob) context.getMergedJobDataMap().get("sysJob");
		JobUtils.invokMethod(sysJob);

	}
}
