package com.silva.chetax.schedule.center.sys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ScheduleStatusEnum {
	SHUTDOWN(0,"",""),
	RUNNING(1,"","")
	;
	private Integer code;
	private String descCn;
	private String descEn;
}
