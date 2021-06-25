package com.silva.chetax.demo.spring.batch.quartz3;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpJobEntity{
	private String id;
	private String url;
	private String requestBody;
	private String cron;
}
