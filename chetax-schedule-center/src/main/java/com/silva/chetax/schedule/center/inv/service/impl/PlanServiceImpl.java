package com.silva.chetax.schedule.center.inv.service.impl;

import java.time.LocalDate;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.silva.chetax.schedule.center.inv.entity.Plan;
import com.silva.chetax.schedule.center.inv.entity.Product;
import com.silva.chetax.schedule.center.inv.in.InventoryAddParams;
import com.silva.chetax.schedule.center.inv.mapper.PlanMapper;
import com.silva.chetax.schedule.center.inv.service.IPlanService;
import com.silva.chetax.schedule.center.inv.service.IProductService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author litinglong
 * @since 2021-07-01
 */
@Service
@Slf4j
public class PlanServiceImpl extends ServiceImpl<PlanMapper, Plan> implements IPlanService {
	@Autowired
	private IProductService iProductService;
	
	@Transactional
	public void addService(InventoryAddParams testIn3) {
		Plan plan = this.getById(testIn3.getPlanId());
		Long num = testIn3.getNum();
		Long totalNum = plan.getTotalNum();
		Long addTotal = plan.getAddTotal();
		Long dayNum = plan.getDayNum();
		Long addDay = plan.getAddDay();
		if(totalNum-addTotal<=0) {
			log.info("总无了，不追加:{}",totalNum-addTotal);
			return;
		}
		if(totalNum-addTotal-num < 0) {
			log.info("因为总的原因只能添加:{}", totalNum-addTotal);
			num = totalNum-addTotal;
		}
		LocalDate lastAddDayDate = plan.getLastAddDayDate();
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
		
		plan.setLastAddDayDate(now);
		plan.setAddDay(addDay + num);
		plan.setAddTotal(addTotal + num);
		Product product = iProductService.getById(plan.getProductId());
		product.setNum(product.getNum() + num);
		this.updateById(plan);
		iProductService.updateById(product);
		log.info("最终成功添加:{}", num);
	}
}
