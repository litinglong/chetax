package com.silva.chetax.schedule.center.inv.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.silva.chetax.schedule.center.inv.in.InventoryAddIn;
import com.silva.chetax.schedule.center.inv.in.InventoryAddParams;
import com.silva.chetax.schedule.center.inv.service.IPlanService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author litinglong
 * @since 2021-07-01
 */
@RestController
@RequestMapping("/inv/plan")
public class PlanController {
	@Autowired
	private IPlanService iPlanService;
	
	@PostMapping("inventoryAdd")
	public String inventoryAdd(@RequestBody InventoryAddIn s) {
		List<InventoryAddParams> addList = s.getAddList();
		for (InventoryAddParams testIn3 : addList) {
			iPlanService.addService(testIn3);
		}
		return s.toString();
	}
}
