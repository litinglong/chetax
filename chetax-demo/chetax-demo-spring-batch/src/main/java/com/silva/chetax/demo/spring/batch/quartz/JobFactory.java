package com.silva.chetax.demo.spring.batch.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @Description: 计划任务执行处 无状态
 * @date 2014年4月24日 下午5:05:47
 */
@Slf4j
public class JobFactory implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SysJob sysJob = (SysJob) context.getMergedJobDataMap().get("sysJob");
		JobUtils.invokMethod(sysJob);
	}
}
