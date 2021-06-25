package com.silva.chetax.demo.spring.batch.quartz;

import lombok.Data;
/**
 * 任务调度Entity
 * @author 李廷龙
 * @version 2017-12-28
 */
@Data
public class SysJob{
	
	private String jobName;		// job_name
	private String jobGroup;		// job_group
	private String jobStatus;		// job_status
	private String cronExpression;		// cron_expression
	private String description;		// description
	private String beanClass;		// bean_class
	private String isConcurrent;		// is_concurrent
	private String springId;		// spring_id
	private String methodName;		// method_name
	
	public static final String STATUS_RUNNING = "0";
	public static final String STATUS_NOT_RUNNING = "1";
	public static final String CONCURRENT_IS = "1";
	public static final String CONCURRENT_NOT = "0";
	
	private String remarks;
}
