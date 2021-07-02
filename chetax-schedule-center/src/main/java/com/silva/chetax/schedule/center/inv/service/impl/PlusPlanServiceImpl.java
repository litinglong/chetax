package com.silva.chetax.schedule.center.inv.service.impl;

import com.silva.chetax.schedule.center.inv.entity.PlusPlan;
import com.silva.chetax.schedule.center.inv.entity.Product;
import com.silva.chetax.schedule.center.inv.in.InventoryAddParams;
import com.silva.chetax.schedule.center.inv.mapper.PlusPlanMapper;
import com.silva.chetax.schedule.center.inv.service.IPlusPlanService;
import com.silva.chetax.schedule.center.inv.service.IProductService;

import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author litinglong
 * @since 2021-07-02
 */
@Service
@Slf4j
public class PlusPlanServiceImpl extends ServiceImpl<PlusPlanMapper, PlusPlan> implements IPlusPlanService {
	@Autowired
	private IProductService iProductService;
	
	@Transactional
	public void addService(InventoryAddParams testIn3) {
		PlusPlan plan = this.getById(testIn3.getPlanId());
		Long num = testIn3.getNum();
		Long totalNum = plan.getTotalNum();
		Long addTotal = plan.getTotalAdd();
		Long dayNum = plan.getDayNum();
		Long addDay = plan.getDayAdd();
		if(totalNum-addTotal<=0) {
			log.info("总无了，不追加:{}",totalNum-addTotal);
			return;
		}
		if(totalNum-addTotal-num < 0) {
			log.info("因为总的原因只能添加:{}", totalNum-addTotal);
			num = totalNum-addTotal;
		}
		LocalDate lastAddDayDate = plan.getLastAddDay();
		LocalDate now =LocalDate.now();
		
		if(!lastAddDayDate.isEqual(now)) {
			addDay = 0L;
		}
		if(dayNum-addDay<=0) {
			log.info("日无了，不追加:{}",dayNum-addDay);
			return;
		}
		if(dayNum-addDay<num) {
			log.info("因为日的原因只能添加:{}", dayNum-addDay);
			num = dayNum-addDay;
		}
		
		plan.setLastAddDay(now);
		plan.setDayAdd(addDay + num);
		plan.setTotalAdd(addTotal + num);
		Product product = iProductService.getById(plan.getProductId());
		product.setInventoryNum(product.getInventoryNum() + num);
		this.updateById(plan);
		iProductService.updateById(product);
		log.info("最终成功添加:{}", num);
	}
}
