package com.silva.chetax.schedule.center.sys.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.silva.chetax.schedule.center.sys.entity.ScheduleInfo;
import com.silva.chetax.schedule.center.sys.enums.ScheduleConcurrentTagEnum;
import com.silva.chetax.schedule.center.sys.enums.ScheduleStatusEnum;
import com.silva.chetax.schedule.center.sys.job.HttpDCEJob;
import com.silva.chetax.schedule.center.sys.job.HttpJob;
import com.silva.chetax.schedule.center.sys.mapper.ScheduleInfoMapper;
import com.silva.chetax.schedule.center.sys.service.IScheduleInfoService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author litinglong
 * @since 2021-06-27
 */
@Service
@Slf4j
public class ScheduleInfoServiceImpl extends ServiceImpl<ScheduleInfoMapper, ScheduleInfo> implements IScheduleInfoService {
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	@PostConstruct
	public void init() throws Exception {
//		// 这里获取任务信息数据
		List<ScheduleInfo> sysJobs = this.list();
		for (ScheduleInfo job : sysJobs) {
			addJob(job);
		}
	}
	
	/**
	 * 添加任务
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void addJob(ScheduleInfo scheduleInfo) throws SchedulerException {
		if (scheduleInfo == null || ScheduleStatusEnum.SHUTDOWN.getCode().equals(scheduleInfo.getStatus())) {
			return;
		}
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleInfo.getJobName(), scheduleInfo.getGroupName());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		// 不存在，创建一个
		if (null == trigger) {
			Class<? extends Job> clazz = ScheduleConcurrentTagEnum.concurrent.getCode().equals(scheduleInfo.getConcurrentTag()) ? HttpJob.class : HttpDCEJob.class;
			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(scheduleInfo.getJobName(), scheduleInfo.getGroupName()).build();
			jobDetail.getJobDataMap().put("scheduleInfo", scheduleInfo);
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleInfo.getCron());
			trigger = TriggerBuilder.newTrigger().withIdentity(scheduleInfo.getJobName(), scheduleInfo.getGroupName()).withSchedule(scheduleBuilder).build();
			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			// Trigger已存在，那么更新相应的定时设置
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleInfo.getCron());
			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}
	}
	@Transactional(readOnly = false)
	public void insertAndAddJob(ScheduleInfo sysJob){
//        if(checkIsBeanExsit(sysJob)){
        	if(checkIsCronRight(sysJob)){
        		save(sysJob);
        		try {
					addJob(sysJob);
				} catch (SchedulerException e) {
				}
        		
            }else{
//            	packet.setMsg("任务调度规则不正确");
            }
//        }else{
//        	packet.setErrorCode();
//        	packet.setMsg("未设置正确的类全路径或者spring实例");
//        }
	}
	
	private boolean checkIsCronRight(ScheduleInfo scheduleInfo){
		try {
			CronScheduleBuilder.cronSchedule(scheduleInfo.getCron());
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
//	private boolean checkIsBeanExsit(ScheduleInfo sysJob){
//		Object obj = null;
//		try {
//			if (StringUtils.isNotBlank(sysJob.getSpringId())) {
//				obj = SpringContextHolder.getBean(sysJob.getSpringId());
//			} else {
//				Class<?> clazz = Class.forName(sysJob.getBeanClass());
//				obj = clazz.newInstance();
//			}
//		} catch (Exception e) {
//		}
//		if (obj == null) {
//			return false;
//		} 
//		return true;
//	}
	
	public PageInfo<ScheduleInfo> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ScheduleInfo> sysJobs = this.baseMapper.selectList(null);
		PageInfo<ScheduleInfo> page = new PageInfo<ScheduleInfo>(sysJobs);
		return page;
	}

	public void executeJob(ScheduleInfo sysJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(sysJob.getJobName(), sysJob.getGroupName());
		scheduler.triggerJob(jobKey);
	}
	/**
	 * 删除一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void deleteJob(ScheduleInfo scheduleInfo) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleInfo.getJobName(), scheduleInfo.getGroupName());
		scheduler.deleteJob(jobKey);
	}
	
	/**
	 * 更改任务状态
	 * cmd{"stop","start"}
	 * @throws SchedulerException
	 */
	@Transactional(readOnly = false)
	public void changeStatus(BigDecimal id, String cmd) throws SchedulerException {
		ScheduleInfo job = this.getById(id);
		if (job == null) {
			return;
		}
		if ("stop".equals(cmd)) {
			deleteJob(job);
			job.setStatus(ScheduleStatusEnum.SHUTDOWN.getCode());
		} else if ("start".equals(cmd)) {
			job.setStatus(ScheduleStatusEnum.RUNNING.getCode());
			addJob(job);
		}
		save(job);
	}
	
	/**
	 * 更新job时间表达式
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void updateJobCron(ScheduleInfo scheduleInfo) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleInfo.getJobName(), scheduleInfo.getGroupName());
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleInfo.getCron());
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		scheduler.rescheduleJob(triggerKey, trigger);
	}
	
	@Transactional(readOnly = false)
	public void updateScheduleInfoCron(ScheduleInfo recieve){
		if(checkIsCronRight(recieve)){
			ScheduleInfo sysJob = this.getById(recieve.getId());
			if(sysJob!=null){
				sysJob.setCron(recieve.getCron());
				save(sysJob);
				if(ScheduleStatusEnum.RUNNING.getCode().equals(sysJob.getStatus())){
					try {
						updateJobCron(sysJob);
					} catch (SchedulerException e) {
						e.printStackTrace();
					}
				}
			}else{
//				packet.setMsg("需要调整规则的任务已不存在");
			}
		}else{
//			packet.setMsg("任务调度规则不正确");
		}
	}
}
