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
public class QuartzJobFactory implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SysScheduleJob scheduleJob = (SysScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		JobUtils.invokMethod(scheduleJob);
	}
}
