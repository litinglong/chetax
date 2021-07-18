package com.silva.chetax.schedule.center.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class MyRuntimeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1474723381619138415L;
	private String errorCode;
	private String errorMsg;
}
