package com.silva.chetax.demo.spring.source.comments.entity.in;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EntityReq {
	private String text;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date time;
}
