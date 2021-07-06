package com.silva.chetax.schedule.center.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.silva.chetax.schedule.center.system.entity.SysScheduleResultEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author litinglong
 * @since 2021-07-06
 */
public interface ISysScheduleResultService extends IService<SysScheduleResultEntity> {
	PageInfo<SysScheduleResultEntity> findSysScheduleResultPage(int pageNum, int pageSize, Long id);
}
