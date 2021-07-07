package com.silva.chetax.schedule.center.common.response;

import lombok.Data;

@Data
public class ChetaxResponse<T> {
	private T data;
	private String code;
	private String msg;
	public ChetaxResponse<T> success(T t){
		ChetaxResponse<T> response = new ChetaxResponse<T>();
		response.setData(t);
		response.setCode("000000");
		response.setMsg("操作成功");
		return response;
	}
}
