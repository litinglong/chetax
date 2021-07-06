package com.silva.chetax.schedule.center.system.service.impl;

import com.silva.chetax.schedule.center.sys.entity.SysScheduleInfoEntity;
import com.silva.chetax.schedule.center.system.entity.SysScheduleResult;
import com.silva.chetax.schedule.center.system.mapper.SysScheduleResultMapper;
import com.silva.chetax.schedule.center.system.service.ISysScheduleResultService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author litinglong
 * @since 2021-07-06
 */
@Service
public class SysScheduleResultServiceImpl extends ServiceImpl<SysScheduleResultMapper, SysScheduleResult> implements ISysScheduleResultService {

	@Override
	public PageInfo<SysScheduleResult> findSysScheduleResultPage(int pageNum, int pageSize, Long id) {
		PageHelper.startPage(pageNum, pageSize);
		LambdaQueryWrapper<SysScheduleResult> lambdaQueryWrapper = Wrappers.<SysScheduleResult>lambdaQuery();
		if(id!=null) {
			lambdaQueryWrapper.eq(SysScheduleResult::getSysScheduleInfoId, id);
		}
		lambdaQueryWrapper.orderByDesc(SysScheduleResult::getStartTime);
		List<SysScheduleResult> sysJobs = this.baseMapper.selectList(lambdaQueryWrapper);
		PageInfo<SysScheduleResult> page = new PageInfo<SysScheduleResult>(sysJobs);
		return page;
	}

}
