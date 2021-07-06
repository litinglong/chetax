package com.silva.chetax.schedule.center.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ScheduleConcurrentTagEnum {
	nonConcurrent(0,"",""),
	concurrent(1,"","")
	;
	private Integer code;
	private String descCn;
	private String descEn;
}
