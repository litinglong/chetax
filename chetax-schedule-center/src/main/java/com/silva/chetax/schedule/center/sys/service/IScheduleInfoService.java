package com.silva.chetax.schedule.center.sys.service;

import com.silva.chetax.schedule.center.sys.entity.ScheduleInfo;
import com.silva.chetax.schedule.center.sys.enums.ScheduleConcurrentTagEnum;
import com.silva.chetax.schedule.center.sys.enums.ScheduleStatusEnum;
import com.silva.chetax.schedule.center.sys.job.HttpDCEJob;
import com.silva.chetax.schedule.center.sys.job.HttpJob;

import java.math.BigDecimal;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author litinglong
 * @since 2021-06-27
 */
public interface IScheduleInfoService extends IService<ScheduleInfo> {
	
	/**
	 * 添加任务
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	void addJob(ScheduleInfo scheduleInfo) throws SchedulerException;
	void insertAndAddJob(ScheduleInfo sysJob);
	
	PageInfo<ScheduleInfo> findPage(int pageNum, int pageSize);

	void executeJob(ScheduleInfo sysJob) throws SchedulerException;
	/**
	 * 删除一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	void deleteJob(ScheduleInfo scheduleInfo) throws SchedulerException;
	
	/**
	 * 更改任务状态
	 * cmd{"stop","start"}
	 * @throws SchedulerException
	 */
	void changeStatus(BigDecimal id, String cmd) throws SchedulerException;
	
	/**
	 * 更新job时间表达式
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	void updateJobCron(ScheduleInfo scheduleInfo) throws SchedulerException;
	
	void updateScheduleInfoCron(ScheduleInfo recieve);
}
