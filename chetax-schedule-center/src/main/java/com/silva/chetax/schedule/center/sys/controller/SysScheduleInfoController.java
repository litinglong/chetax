package com.silva.chetax.schedule.center.sys.controller;


import java.math.BigDecimal;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.silva.chetax.schedule.center.sys.entity.SysScheduleInfoEntity;
import com.silva.chetax.schedule.center.sys.service.ISysScheduleInfoService;

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
public class SysScheduleInfoController {
	@Autowired
	ISysScheduleInfoService iScheduleInfoService;
	
	@GetMapping("getScheduleInfoById/{id}")
	SysScheduleInfoEntity getScheduleInfoById(@PathVariable("id") BigDecimal id){
		return iScheduleInfoService.getScheduleInfoById(id);
	}
	
	@GetMapping("deleteScheduleInfoById/{id}")
	void deleteScheduleInfoById(@PathVariable("id") BigDecimal id) throws SchedulerException{
		iScheduleInfoService.deleteScheduleInfoById(id);
	}
	
	@PostMapping("insertAndAddJob")
	void insertAndAddJob(@RequestBody SysScheduleInfoEntity scheduleInfo){
		iScheduleInfoService.insertAndAddJob(scheduleInfo);
	}
	@PostMapping("findPage/{pageNum}/{pageSize}")
	PageInfo<SysScheduleInfoEntity> findPage(@PathVariable("pageNum") int pageNum,
			@PathVariable("pageSize")  int pageSize){
		PageInfo<SysScheduleInfoEntity> pageInfo = iScheduleInfoService.findPage(pageNum,pageSize);
		return pageInfo;
	}
	@GetMapping("executeJob/{id}")
	void executeJob(@PathVariable("id") BigDecimal id) throws SchedulerException{
		iScheduleInfoService.executeById(id);
	}
	
	@GetMapping("changeStatus/{id}")
	void changeStatus(@PathVariable("id") BigDecimal id) throws SchedulerException{
		iScheduleInfoService.changeStatus(id);
	}
	@PostMapping("updateScheduleInfoCron/{id}")
	void updateScheduleInfoCron(@PathVariable("id") BigDecimal id, @RequestParam("cron") String cron){
		iScheduleInfoService.updateScheduleInfoCron(id,cron);
	}
}
