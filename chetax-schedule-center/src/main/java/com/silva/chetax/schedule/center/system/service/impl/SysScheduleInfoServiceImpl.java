package com.silva.chetax.schedule.center.system.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.silva.chetax.schedule.center.system.entity.SysScheduleInfoEntity;
import com.silva.chetax.schedule.center.system.entity.SysScheduleResultEntity;
import com.silva.chetax.schedule.center.system.enums.ScheduleConcurrentTagEnum;
import com.silva.chetax.schedule.center.system.enums.ScheduleStatusEnum;
import com.silva.chetax.schedule.center.system.mapper.SysScheduleInfoMapper;
import com.silva.chetax.schedule.center.system.service.ISysScheduleInfoService;
import com.silva.chetax.schedule.center.system.service.ISysScheduleResultService;
import com.silva.chetax.schedule.center.system.task.HttpDceTask;
import com.silva.chetax.schedule.center.system.task.HttpTask;
import com.silva.chetax.schedule.center.utils.ExceptionUtils;
import com.silva.chetax.schedule.center.utils.TimeUtils;

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
public class SysScheduleInfoServiceImpl extends ServiceImpl<SysScheduleInfoMapper, SysScheduleInfoEntity> implements ISysScheduleInfoService {
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	@Autowired
	private ISysScheduleResultService iSysScheduleResultService;
	
	@PostConstruct
	public void init() throws Exception {
//		// 这里获取任务信息数据
		List<SysScheduleInfoEntity> sysJobs = this.list();
		for (SysScheduleInfoEntity job : sysJobs) {
			startJob(job);
		}
	}
	public SysScheduleInfoEntity getScheduleInfoById(BigDecimal id) {
		return this.getById(id);
	}
	
	public void deleteScheduleInfoById(BigDecimal id) {
		SysScheduleInfoEntity scheduleInfo = this.getById(id);
		if(scheduleInfo != null) {
			try {
				stopJob(scheduleInfo);
			} catch (SchedulerException e) {
			}
			this.removeById(id);
		}
		
	}
	/**
	 * 添加任务
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void startJob(SysScheduleInfoEntity scheduleInfo) throws SchedulerException {
		if (scheduleInfo == null || ScheduleStatusEnum.SHUTDOWN.getCode().equals(scheduleInfo.getStatus())) {
			return;
		}
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleInfo.getJobName(), scheduleInfo.getGroupName());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		// 不存在，创建一个
		if (null == trigger) {
			Class<? extends Job> clazz = ScheduleConcurrentTagEnum.concurrent.getCode().equals(scheduleInfo.getConcurrentTag()) ? HttpTask.class : HttpDceTask.class;
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
	public void insertAndAddJob(SysScheduleInfoEntity sysJob){
    	if(checkIsCronRight(sysJob.getCron())){
    		save(sysJob);
    		try {
				startJob(sysJob);
			} catch (SchedulerException e) {
			}
    		
        }else{
        }
	}
	
	public void insertSysScheduleInfoEntity(SysScheduleInfoEntity sysScheduleInfoEntity){
      	if(checkIsCronRight(sysScheduleInfoEntity.getCron())){
	  		this.save(sysScheduleInfoEntity);
	  		try {
				startJob(sysScheduleInfoEntity);
			} catch (SchedulerException e) {
			}
      	}
	}
	
	public void updateSysScheduleInfoEntity(SysScheduleInfoEntity sysScheduleInfoEntity) {
		if(checkIsCronRight(sysScheduleInfoEntity.getCron())){
	  		this.updateById(sysScheduleInfoEntity);
      	}
	}
	
	private boolean checkIsCronRight(String cron){
		try {
			CronScheduleBuilder.cronSchedule(cron);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
	public PageInfo<SysScheduleInfoEntity> findSysScheduleInfoPage(int pageNum, int pageSize, SysScheduleInfoEntity sysScheduleInfoEntity) {
		PageHelper.startPage(pageNum, pageSize);
		
		LambdaQueryWrapper<SysScheduleInfoEntity> wrapper = Wrappers.<SysScheduleInfoEntity>lambdaQuery();
		if(StringUtils.isNotBlank(sysScheduleInfoEntity.getJobName())) {
			wrapper.likeRight(SysScheduleInfoEntity::getJobName, sysScheduleInfoEntity.getJobName());
		}
		if(StringUtils.isNotBlank(sysScheduleInfoEntity.getGroupName())) {
			wrapper.likeRight(SysScheduleInfoEntity::getGroupName, sysScheduleInfoEntity.getGroupName());
		}
		if(StringUtils.isNotBlank(sysScheduleInfoEntity.getDescription())) {
			wrapper.likeRight(SysScheduleInfoEntity::getDescription, sysScheduleInfoEntity.getDescription());
		}
		
		if(ObjectUtils.isNotNull(sysScheduleInfoEntity.getConcurrentTag())) {
			wrapper.eq(SysScheduleInfoEntity::getConcurrentTag, sysScheduleInfoEntity.getConcurrentTag());
		}
		if(ObjectUtils.isNotNull(sysScheduleInfoEntity.getStatus())) {
			wrapper.eq(SysScheduleInfoEntity::getStatus, sysScheduleInfoEntity.getStatus());
		}
		wrapper.orderByDesc(SysScheduleInfoEntity::getCreateTime);
		List<SysScheduleInfoEntity> sysJobs = this.baseMapper.selectList(wrapper);
		PageInfo<SysScheduleInfoEntity> page = new PageInfo<SysScheduleInfoEntity>(sysJobs);
		return page;
	}

	public void executeById(BigDecimal id) throws SchedulerException {
		SysScheduleInfoEntity scheduleInfo = this.getById(id);
		if (scheduleInfo == null) {
			return;
		}
		doExecuteJob(scheduleInfo);
	}
	
	public void doExecuteJob(SysScheduleInfoEntity scheduleInfo){
		if (scheduleInfo == null) {
			return;
		}
		SysScheduleResultEntity sysScheduleResult = new SysScheduleResultEntity();
		LocalDateTime startTime = LocalDateTime.now();
		sysScheduleResult.setStartTime(startTime);
		sysScheduleResult.setRequestBody(scheduleInfo.getRequestBody());
		sysScheduleResult.setSysScheduleInfoId(scheduleInfo.getId());
		sysScheduleResult.setUrl(scheduleInfo.getUrl());
		sysScheduleResult.setCreateTime(startTime);
		sysScheduleResult.setCreateUser("-1");
		iSysScheduleResultService.save(sysScheduleResult);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<String>(scheduleInfo.getRequestBody(), headers);
        try {
        	ResponseEntity<String> responseEntity = restTemplate.postForEntity(scheduleInfo.getUrl(), requestEntity, String.class, "");
        	String responseBody = responseEntity.getBody();
        	String responseBodySub = StringUtils.substring(responseBody, 0, 10000);
        	sysScheduleResult.setResultMsg(responseBodySub);
        } catch (Exception e) {
			String exceptionString = ExceptionUtils.stackTraceToString(e);
			String exceptionStringSub = StringUtils.substring(exceptionString, 0, 10000);
			sysScheduleResult.setExceptionMsg(exceptionStringSub);
		} finally {
			LocalDateTime endTime = LocalDateTime.now();
			sysScheduleResult.setEndTime(endTime);
			Long usedTime = TimeUtils.usedTime(startTime, endTime);
			String usedTimeFormated = TimeUtils.formateMs(usedTime);
			sysScheduleResult.setUsedTimeFormated(usedTimeFormated);
			sysScheduleResult.setUsedTime(usedTime);
			sysScheduleResult.setUpdateTime(endTime);
			sysScheduleResult.setUpdateUser("-1");
			iSysScheduleResultService.updateById(sysScheduleResult);
		}
       
	}
	
	public void stopJob(SysScheduleInfoEntity scheduleInfo) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleInfo.getJobName(), scheduleInfo.getGroupName());
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleInfo.getJobName(), scheduleInfo.getGroupName());
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		if (null != trigger) {
			scheduler.deleteJob(jobKey);
		}
	}
	
	@Transactional(readOnly = false)
	public void changeStatusOfSysScheduleInfo(BigDecimal id) throws SchedulerException {
		SysScheduleInfoEntity job = this.getById(id);
		if (job == null) {
			return;
		}
		if (ScheduleStatusEnum.RUNNING.getCode().equals(job.getStatus())) {
			job.setStatus(ScheduleStatusEnum.SHUTDOWN.getCode());
			this.updateById(job);
			stopJob(job);
		} else if (ScheduleStatusEnum.SHUTDOWN.getCode().equals(job.getStatus())) {
			job.setStatus(ScheduleStatusEnum.RUNNING.getCode());
			this.updateById(job);
			startJob(job);
		}
	}
	
	public void updateJobCron(SysScheduleInfoEntity scheduleInfo) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleInfo.getJobName(), scheduleInfo.getGroupName());
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleInfo.getCron());
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		scheduler.rescheduleJob(triggerKey, trigger);
	}
	
	@Transactional(readOnly = false)
	public void updateScheduleInfoCron(BigDecimal id,String cron){
		if(checkIsCronRight(cron)){
			SysScheduleInfoEntity sysJob = this.getById(id);
			if(sysJob!=null){
				sysJob.setCron(cron);
				this.updateById(sysJob);
				if(ScheduleStatusEnum.RUNNING.getCode().equals(sysJob.getStatus())){
					try {
						updateJobCron(sysJob);
					} catch (SchedulerException e) {
					}
				}
			}
		}
	}
}
