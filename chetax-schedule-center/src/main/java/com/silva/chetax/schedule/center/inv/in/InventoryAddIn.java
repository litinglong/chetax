package com.silva.chetax.schedule.center.inv.in;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InventoryAddIn {
	private List<InventoryAddParams> addList;
}
