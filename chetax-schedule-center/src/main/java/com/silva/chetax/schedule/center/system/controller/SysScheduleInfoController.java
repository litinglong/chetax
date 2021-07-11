package com.silva.chetax.schedule.center.system.controller;


import java.math.BigDecimal;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.silva.chetax.schedule.center.common.response.BusinessLog;
import com.silva.chetax.schedule.center.common.response.KingIn;
import com.silva.chetax.schedule.center.system.entity.SysScheduleInfoEntity;
import com.silva.chetax.schedule.center.system.entity.SysTransInfoEntity;
import com.silva.chetax.schedule.center.system.service.ISysScheduleInfoService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author litinglong
 * @since 2021-06-27
 */
@RestController
@RequestMapping("/system/scheduleInfoController")
@Slf4j
public class SysScheduleInfoController {
	@Autowired
	ISysScheduleInfoService iScheduleInfoService;
	
	@GetMapping("getScheduleInfoById/{id}")
	public SysScheduleInfoEntity getScheduleInfoById(@PathVariable("id") BigDecimal id){
		return iScheduleInfoService.getScheduleInfoById(id);
	}
	
	@GetMapping("deleteScheduleInfoById/{id}")
	public void deleteScheduleInfoById(@PathVariable("id") BigDecimal id) throws SchedulerException{
		iScheduleInfoService.deleteScheduleInfoById(id);
	}
	//https://blog.csdn.net/weixin_42236404/article/details/105653432
	@PostMapping("insertSysScheduleInfoEntity")
	public String insertSysScheduleInfoEntity(@Validated @RequestBody SysScheduleInfoEntity scheduleInfo, BindingResult bindingResult){
		iScheduleInfoService.insertSysScheduleInfoEntity(scheduleInfo);
		return bindingResult.getFieldError().getDefaultMessage();
	}
	
	@PostMapping("updateSysScheduleInfoEntity")
	public String updateSysScheduleInfoEntity(@Validated @RequestBody SysScheduleInfoEntity scheduleInfo, BindingResult bindingResult){
		iScheduleInfoService.updateSysScheduleInfoEntity(scheduleInfo);
		return bindingResult.getFieldError().getDefaultMessage();
	}
	
	@PostMapping("findSysScheduleInfoPage/{pageNum}/{pageSize}")
	@BusinessLog
	public PageInfo<SysScheduleInfoEntity> findSysScheduleInfoPage(@PathVariable("pageNum") int pageNum,
			@PathVariable("pageSize")  int pageSize, @RequestBody SysScheduleInfoEntity sysScheduleInfoEntity,SysTransInfoEntity sysTransInfo,KingIn kingIn){
		PageInfo<SysScheduleInfoEntity> pageInfo = iScheduleInfoService.findSysScheduleInfoPage(pageNum, pageSize, sysScheduleInfoEntity);
		return pageInfo;
	}
	
	@GetMapping("executeTask/{id}")
	@BusinessLog
	public void executeTask(@PathVariable("id") BigDecimal id) throws SchedulerException{
		iScheduleInfoService.executeById(id);
	}
	
	@GetMapping("changeStatusOfSysScheduleInfo/{id}")
	public void changeStatusOfSysScheduleInfo(@PathVariable("id") BigDecimal id) throws SchedulerException{
		iScheduleInfoService.changeStatusOfSysScheduleInfo(id);
	}
	
	@PostMapping("updateScheduleInfoCron/{id}")
	public void updateScheduleInfoCron(@PathVariable("id") BigDecimal id, @RequestParam("cron") String cron){
		iScheduleInfoService.updateScheduleInfoCron(id,cron);
	}
}
