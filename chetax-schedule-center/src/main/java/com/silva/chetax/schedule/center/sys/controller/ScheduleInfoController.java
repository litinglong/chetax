package com.silva.chetax.schedule.center.sys.controller;


import java.math.BigDecimal;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.silva.chetax.schedule.center.sys.entity.ScheduleInfo;
import com.silva.chetax.schedule.center.sys.service.IScheduleInfoService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author litinglong
 * @since 2021-06-27
 */
@RestController
@RequestMapping("/sys/scheduleInfoController")
public class ScheduleInfoController {
	@Autowired
	IScheduleInfoService iScheduleInfoService;
	/**
	 * 添加任务
	 */
	@PostMapping("addJob")
	void addJob(@RequestBody ScheduleInfo scheduleInfo) throws SchedulerException{
		iScheduleInfoService.addJob(scheduleInfo);
	}
	@PostMapping("insertAndAddJob")
	void insertAndAddJob(@RequestBody ScheduleInfo scheduleInfo){
		iScheduleInfoService.insertAndAddJob(scheduleInfo);
	}
	@PostMapping("findPage/{pageNum}/{pageSize}")
	PageInfo<ScheduleInfo> findPage(@PathVariable("pageNum") int pageNum,
			@PathVariable("pageSize")  int pageSize){
		PageInfo<ScheduleInfo> pageInfo = iScheduleInfoService.findPage(pageNum,pageSize);
		return pageInfo;
	}
	@PostMapping("executeJob")
	void executeJob(@RequestBody ScheduleInfo scheduleInfo) throws SchedulerException{
		iScheduleInfoService.executeJob(scheduleInfo);
	}
	@PostMapping("deleteJob")
	void deleteJob(@RequestBody ScheduleInfo scheduleInfo) throws SchedulerException{
		iScheduleInfoService.deleteJob(scheduleInfo);
	}
	
	@PostMapping("changeStatus")
	void changeStatus(BigDecimal id, String cmd) throws SchedulerException{
		iScheduleInfoService.changeStatus(id,cmd);
	}
	@PostMapping("updateJobCron")
	void updateJobCron(@RequestBody ScheduleInfo scheduleInfo) throws SchedulerException{
		iScheduleInfoService.updateJobCron(scheduleInfo);
	}
	@PostMapping("updateScheduleInfoCron")
	void updateScheduleInfoCron(@RequestBody ScheduleInfo recieve){
		iScheduleInfoService.updateScheduleInfoCron(recieve);
	}
}
