package com.silva.chetax.schedule.center.inv.in;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InventoryAddParams {
	private String planId;
	private Long num;
}
