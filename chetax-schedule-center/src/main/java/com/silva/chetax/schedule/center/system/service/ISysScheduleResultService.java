package com.silva.chetax.schedule.center.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.silva.chetax.schedule.center.system.entity.SysScheduleResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author litinglong
 * @since 2021-07-06
 */
public interface ISysScheduleResultService extends IService<SysScheduleResult> {
	PageInfo<SysScheduleResult> findSysScheduleResultPage(int pageNum, int pageSize, Long id);
}
