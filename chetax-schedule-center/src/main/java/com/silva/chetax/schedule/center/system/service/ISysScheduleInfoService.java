package com.silva.chetax.schedule.center.system.service;

import java.math.BigDecimal;

import org.quartz.SchedulerException;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.silva.chetax.schedule.center.system.entity.SysScheduleInfoEntity;

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
	void insertSysScheduleInfoEntity(SysScheduleInfoEntity sysScheduleInfoEntity);
	
	PageInfo<SysScheduleInfoEntity> findSysScheduleInfoPage(int pageNum, int pageSize, SysScheduleInfoEntity sysScheduleInfoEntity);

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
