package com.silva.chetax.demo.spring.batch.quartz3;

import java.util.ArrayList;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
//手动配置，这里演示CronScheduleBuilder
//https://www.cnblogs.com/summerday152/p/14193968.html
@Component
public class JobInitCenter implements ApplicationRunner {
    @Autowired
    private Scheduler scheduler;
    @Override
    public void run(ApplicationArguments args) throws Exception {
    	List<HttpJobEntity> list = new ArrayList<HttpJobEntity>();
    	HttpJobEntity entity1 = new HttpJobEntity("1", "http://www.baidu.com", "{'var1':'x'}", "0/5 * * * * ? *");
    	HttpJobEntity entity2 = new HttpJobEntity("2", "http://www.baidu.com", "{'var1':'x'}", "0/10 * * * * ? *");
    	list.add(entity1);
    	list.add(entity2);
    	String jobDetailTag = "jobDetailTag";
    	String triggerTag = "triggerTag";
    	String pattern = "%s.%s";
    	for (HttpJobEntity entity : list) {
    		JobDetail jobDetail = JobBuilder.newJob(HttpJob.class)
                    .withIdentity(String.format(pattern, jobDetailTag, entity.getId()))
                    .storeDurably()
                    .build();
    		jobDetail.getJobDataMap().put("HttpJobEntity", entity);
            Trigger trigger = TriggerBuilder.newTrigger()
                    .forJob(jobDetail)
                    .withIdentity(String.format(pattern, triggerTag, entity.getId()))
                    .withSchedule(CronScheduleBuilder.cronSchedule(entity.getCron()))
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
		}
    }
}
