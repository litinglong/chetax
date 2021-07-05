package com.silva.chetax.schedule.center.sys.service;

import com.silva.chetax.schedule.center.sys.entity.SysScheduleInfoEntity;
import com.silva.chetax.schedule.center.sys.enums.ScheduleConcurrentTagEnum;
import com.silva.chetax.schedule.center.sys.enums.ScheduleStatusEnum;
import com.silva.chetax.schedule.center.sys.job.HttpDceTask;
import com.silva.chetax.schedule.center.sys.job.HttpTask;

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
import org.springframework.web.bind.annotation.PathVariable;

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
public interface ISysScheduleInfoService extends IService<SysScheduleInfoEntity> {
	SysScheduleInfoEntity getScheduleInfoById(BigDecimal id);
	void deleteScheduleInfoById(BigDecimal id);
	/**
	 * 添加任务
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	void startJob(SysScheduleInfoEntity scheduleInfo) throws SchedulerException;
	void insertAndAddJob(SysScheduleInfoEntity sysJob);
	
	PageInfo<SysScheduleInfoEntity> findPage(int pageNum, int pageSize);

	void executeById(BigDecimal id) throws SchedulerException;
	void doExecuteJob(SysScheduleInfoEntity ScheduleInfo);
	/**
	 * 删除一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	void stopJob(SysScheduleInfoEntity scheduleInfo) throws SchedulerException;
	
	/**
	 * 更改任务状态
	 * cmd{"stop","start"}
	 * @throws SchedulerException
	 */
	void changeStatus(BigDecimal id) throws SchedulerException;
	
	/**
	 * 更新job时间表达式
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	void updateJobCron(SysScheduleInfoEntity scheduleInfo) throws SchedulerException;
	
	void updateScheduleInfoCron(BigDecimal id,String cron);
}
