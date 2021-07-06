package com.silva.chetax.schedule.center.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.silva.chetax.schedule.center.system.entity.SysScheduleResult;
import com.silva.chetax.schedule.center.system.service.ISysScheduleResultService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author litinglong
 * @since 2021-07-06
 */
@RestController
@RequestMapping("/system/sysScheduleResultController")
public class SysScheduleResultController {
	@Autowired
	ISysScheduleResultService iSysScheduleResultService;
	@PostMapping("findSysScheduleResultPage/{pageNum}/{pageSize}")
	public PageInfo<SysScheduleResult> findSysScheduleResultPage(@PathVariable("pageNum")int pageNum,
			@PathVariable("pageSize") int pageSize,@RequestParam("id") Long id){
		return iSysScheduleResultService.findSysScheduleResultPage(pageNum,pageSize,id);
	}
}
