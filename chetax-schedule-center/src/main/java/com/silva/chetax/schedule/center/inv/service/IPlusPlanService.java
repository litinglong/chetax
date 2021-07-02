package com.silva.chetax.schedule.center.inv.service;

import com.silva.chetax.schedule.center.inv.entity.PlusPlan;
import com.silva.chetax.schedule.center.inv.in.InventoryAddParams;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author litinglong
 * @since 2021-07-02
 */
public interface IPlusPlanService extends IService<PlusPlan> {
	void addService(InventoryAddParams testIn3);
}
