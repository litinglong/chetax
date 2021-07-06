package com.silva.chetax.schedule.center.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.silva.chetax.schedule.center.system.entity.SysScheduleResultEntity;
import com.silva.chetax.schedule.center.system.mapper.SysScheduleResultMapper;
import com.silva.chetax.schedule.center.system.service.ISysScheduleResultService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author litinglong
 * @since 2021-07-06
 */
@Service
public class SysScheduleResultServiceImpl extends ServiceImpl<SysScheduleResultMapper, SysScheduleResultEntity> implements ISysScheduleResultService {

	@Override
	public PageInfo<SysScheduleResultEntity> findSysScheduleResultPage(int pageNum, int pageSize, Long id) {
		PageHelper.startPage(pageNum, pageSize);
		LambdaQueryWrapper<SysScheduleResultEntity> lambdaQueryWrapper = Wrappers.<SysScheduleResultEntity>lambdaQuery();
		if(id!=null) {
			lambdaQueryWrapper.eq(SysScheduleResultEntity::getSysScheduleInfoId, id);
		}
		lambdaQueryWrapper.orderByDesc(SysScheduleResultEntity::getStartTime);
		List<SysScheduleResultEntity> sysJobs = this.baseMapper.selectList(lambdaQueryWrapper);
		PageInfo<SysScheduleResultEntity> page = new PageInfo<SysScheduleResultEntity>(sysJobs);
		return page;
	}

}
