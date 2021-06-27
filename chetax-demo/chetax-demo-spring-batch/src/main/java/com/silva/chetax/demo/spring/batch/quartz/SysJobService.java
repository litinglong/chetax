//package com.silva.chetax.demo.spring.batch.quartz;
//
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//
//import org.apache.log4j.Logger;
//import org.quartz.CronScheduleBuilder;
//import org.quartz.CronTrigger;
//import org.quartz.Job;
//import org.quartz.JobBuilder;
//import org.quartz.JobDetail;
//import org.quartz.JobKey;
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.quartz.TriggerBuilder;
//import org.quartz.TriggerKey;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.chuyutech.alpha.common.persistence.Page;
//import com.chuyutech.alpha.common.service.CrudService;
//import com.chuyutech.alpha.common.utils.SpringContextHolder;
//import com.chuyutech.alpha.common.utils.StringUtils;
//import com.chuyutech.alpha.modules.sys.dao.job.SysJobDao;
//import com.chuyutech.alpha.modules.sys.entity.job.SysJob;
//import com.chuyutech.alpha.modules.sys.job.JobFactory;
//import com.chuyutech.alpha.modules.sys.job.JobFactoryDisallowConcurrentExecution;
//import com.chuyutech.alpha.modules.sys.packet.Packet;
//
///**
// * 
// * @author 李廷龙
// * @version 2017-12-29
// */
////@Service
////@Transactional(readOnly = true)
//public class SysJobService extends CrudService<SysJobDao, SysJob> {
//	public final Logger log = Logger.getLogger(this.getClass());
//	@Autowired
//	private SchedulerFactoryBean schedulerFactoryBean;
//	@PostConstruct
//	public void init() throws Exception {
//		System.out.println("SysJobService:init");
////		// 这里获取任务信息数据
//		List<SysJob> sysJobs = findList(new SysJob());
//		for (SysJob job : sysJobs) {
//			addJob(job);
//		}
//	}
//	/**
//	 * 添加任务
//	 * 
//	 * @param scheduleJob
//	 * @throws SchedulerException
//	 */
//	public void addJob(SysJob sysJob) throws SchedulerException {
//		if (sysJob == null || SysJob.STATUS_NOT_RUNNING.equals(sysJob.getJobStatus())) {
//			return;
//		}
//
//		Scheduler scheduler = schedulerFactoryBean.getScheduler();
//		log.debug(scheduler + ".......................................................................................add");
//		TriggerKey triggerKey = TriggerKey.triggerKey(sysJob.getJobName(), sysJob.getJobGroup());
//
//		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//
//		// 不存在，创建一个
//		if (null == trigger) {
//			Class<? extends Job> clazz = SysJob.CONCURRENT_IS.equals(sysJob.getIsConcurrent()) ? JobFactory.class : JobFactoryDisallowConcurrentExecution.class;
//			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(sysJob.getJobName(), sysJob.getJobGroup()).build();
//			jobDetail.getJobDataMap().put("sysJob", sysJob);
//			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(sysJob.getCronExpression());
//			trigger = TriggerBuilder.newTrigger().withIdentity(sysJob.getJobName(), sysJob.getJobGroup()).withSchedule(scheduleBuilder).build();
//			scheduler.scheduleJob(jobDetail, trigger);
//		} else {
//			// Trigger已存在，那么更新相应的定时设置
//			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(sysJob.getCronExpression());
//			// 按新的cronExpression表达式重新构建trigger
//			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
//			// 按新的trigger重新设置job执行
//			scheduler.rescheduleJob(triggerKey, trigger);
//		}
//	}
//	@Transactional(readOnly = false)
//	public Packet insertAndaddJob(SysJob sysJob){
//        Packet packet=new Packet();
//        if(checkIsBeanExsit(sysJob)){
//        	if(checkIsCronRight(sysJob)){
//        		save(sysJob);
//        		try {
//					addJob(sysJob);
//				} catch (SchedulerException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					packet.setErrorCode();
//	            	packet.setMsg(e.getMessage());
//				}
//        		
//            }else{
//            	packet.setErrorCode();
//            	packet.setMsg("任务调度规则不正确");
//            }
//        }else{
//        	packet.setErrorCode();
//        	packet.setMsg("未设置正确的类全路径或者spring实例");
//        }
//        return packet;
//	}
//	
//	private boolean checkIsCronRight(SysJob sysJob){
//		try {
//			CronScheduleBuilder.cronSchedule(sysJob.getCronExpression());
//		} catch (Exception e) {
//			return false;
//		}
//		return true;
//	}
//	
//	private boolean checkIsBeanExsit(SysJob sysJob){
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
//	
//	public SysJob get(String id) {
//		return super.get(id);
//	}
//
//	public List<SysJob> findList(SysJob sysJob) {
//		return super.findList(sysJob);
//	}
//
//	public Page<SysJob> findPage(Page<SysJob> page, SysJob sysJob) {
//		return super.findPage(page, sysJob);
//	}
//
//	@Transactional(readOnly = false)
//	public void save(SysJob sysJob) {
//		super.save(sysJob);
//	}
//
//	@Transactional(readOnly = false)
//	public void delete(SysJob sysJob) {
//		super.delete(sysJob);
//	}
//	
//	public void executeJob(SysJob sysJob) throws SchedulerException {
//		Scheduler scheduler = schedulerFactoryBean.getScheduler();
//		JobKey jobKey = JobKey.jobKey(sysJob.getJobName(), sysJob.getJobGroup());
//		scheduler.triggerJob(jobKey);
//	}
//	/**
//	 * 删除一个job
//	 * 
//	 * @param scheduleJob
//	 * @throws SchedulerException
//	 */
//	public void deleteJob(SysJob sysJob) throws SchedulerException {
//		Scheduler scheduler = schedulerFactoryBean.getScheduler();
//		JobKey jobKey = JobKey.jobKey(sysJob.getJobName(), sysJob.getJobGroup());
//		scheduler.deleteJob(jobKey);
//	}
//	
//	/**
//	 * 更改任务状态
//	 * cmd{"stop","start"}
//	 * @throws SchedulerException
//	 */
//	@Transactional(readOnly = false)
//	public void changeStatus(String id, String cmd) throws SchedulerException {
//		SysJob job = get(id);
//		if (job == null) {
//			return;
//		}
//		if ("stop".equals(cmd)) {
//			deleteJob(job);
//			job.setJobStatus(SysJob.STATUS_NOT_RUNNING);
//		} else if ("start".equals(cmd)) {
//			job.setJobStatus(SysJob.STATUS_RUNNING);
//			addJob(job);
//		}
//		save(job);
//	}
//	
//	/**
//	 * 更新job时间表达式
//	 * 
//	 * @param scheduleJob
//	 * @throws SchedulerException
//	 */
//	public void updateJobCron(SysJob sysJob) throws SchedulerException {
//		Scheduler scheduler = schedulerFactoryBean.getScheduler();
//		TriggerKey triggerKey = TriggerKey.triggerKey(sysJob.getJobName(), sysJob.getJobGroup());
//		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(sysJob.getCronExpression());
//		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
//		scheduler.rescheduleJob(triggerKey, trigger);
//	}
//	
//	@Transactional(readOnly = false)
//	public Packet updateSysJobCronExpression(SysJob recieve){
//		Packet packet=new Packet();
//		if(checkIsCronRight(recieve)){
//			SysJob sysJob=get(recieve.getId());
//			if(sysJob!=null){
//				sysJob.setCronExpression(recieve.getCronExpression());
//				save(sysJob);
//				if(SysJob.STATUS_RUNNING.equals(sysJob.getJobStatus())){
//					try {
//						updateJobCron(sysJob);
//					} catch (SchedulerException e) {
//						e.printStackTrace();
//						packet.setErrorInfo();
//					}
//				}
//			}else{
//				packet.setErrorCode();
//				packet.setMsg("需要调整规则的任务已不存在");
//			}
//		}else{
//			packet.setErrorCode();
//			packet.setMsg("任务调度规则不正确");
//		}
//		return packet;
//	}
//	
//
//}
