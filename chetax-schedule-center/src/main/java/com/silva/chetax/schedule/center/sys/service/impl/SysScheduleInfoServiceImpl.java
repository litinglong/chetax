package com.silva.chetax.schedule.center.sys.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.silva.chetax.schedule.center.sys.entity.SysScheduleInfoEntity;
import com.silva.chetax.schedule.center.sys.enums.ScheduleConcurrentTagEnum;
import com.silva.chetax.schedule.center.sys.enums.ScheduleStatusEnum;
import com.silva.chetax.schedule.center.sys.job.HttpDceTask;
import com.silva.chetax.schedule.center.sys.job.HttpTask;
import com.silva.chetax.schedule.center.sys.mapper.SysScheduleInfoMapper;
import com.silva.chetax.schedule.center.sys.service.ISysScheduleInfoService;
import com.silva.chetax.schedule.center.system.entity.SysScheduleResult;
import com.silva.chetax.schedule.center.system.service.ISysScheduleResultService;

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
//        if(checkIsBeanExsit(sysJob)){
        	if(checkIsCronRight(sysJob.getCron())){
        		save(sysJob);
        		try {
					startJob(sysJob);
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
	
	public void insertSysScheduleInfoEntity(SysScheduleInfoEntity sysScheduleInfoEntity){
      	if(checkIsCronRight(sysScheduleInfoEntity.getCron())){
      		this.save(sysScheduleInfoEntity);
      		try {
				startJob(sysScheduleInfoEntity);
			} catch (SchedulerException e) {
			}
      		
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
	
	public PageInfo<SysScheduleInfoEntity> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<SysScheduleInfoEntity> sysJobs = this.baseMapper.selectList(null);
		PageInfo<SysScheduleInfoEntity> page = new PageInfo<SysScheduleInfoEntity>(sysJobs);
		return page;
	}

//	public void executeJob(BigDecimal id) throws SchedulerException {
//		ScheduleInfo ScheduleInfo = this.getById(id);
//		if (ScheduleInfo == null) {
//			return;
//		}
//		Scheduler scheduler = schedulerFactoryBean.getScheduler();
//		JobKey jobKey = JobKey.jobKey(ScheduleInfo.getJobName(), ScheduleInfo.getGroupName());
//		scheduler.triggerJob(jobKey);
//		
//	}
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
		SysScheduleResult sysScheduleResult = new SysScheduleResult();
		
		sysScheduleResult.setStartTime(LocalDateTime.now());
		sysScheduleResult.setRequestBody(scheduleInfo.getRequestBody());
		sysScheduleResult.setSysScheduleInfoId(scheduleInfo.getId());
		sysScheduleResult.setUrl(scheduleInfo.getUrl());
		iSysScheduleResultService.save(sysScheduleResult);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<String>(scheduleInfo.getRequestBody(), headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(scheduleInfo.getUrl(), requestEntity, String.class, "");
        log.info("url >> {} >> {}", scheduleInfo.getUrl(), responseEntity.getBody());
        sysScheduleResult.setResultMsg(responseEntity.getBody());
		sysScheduleResult.setExceptionMsg(null);
		sysScheduleResult.setEndTime(LocalDateTime.now());
		iSysScheduleResultService.updateById(sysScheduleResult);
	}
	/**
	 * 删除一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void stopJob(SysScheduleInfoEntity scheduleInfo) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleInfo.getJobName(), scheduleInfo.getGroupName());
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleInfo.getJobName(), scheduleInfo.getGroupName());
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		if (null != trigger) {
			scheduler.deleteJob(jobKey);
		}
	}
	
	/**
	 * 更改任务状态
	 * cmd{"stop","start"}
	 * @throws SchedulerException
	 */
	@Transactional(readOnly = false)
	public void changeStatus(BigDecimal id) throws SchedulerException {
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
	
	/**
	 * 更新job时间表达式
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
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
			}else{
//				packet.setMsg("需要调整规则的任务已不存在");
			}
		}else{
//			packet.setMsg("任务调度规则不正确");
		}
	}
}
