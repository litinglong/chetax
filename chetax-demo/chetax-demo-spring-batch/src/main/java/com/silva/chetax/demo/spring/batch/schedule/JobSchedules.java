package com.silva.chetax.demo.spring.batch.schedule;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JobSchedules {
	@Autowired
	private JobOperator jobOperator;
	@Scheduled(cron = "${appscheduled.cron.test}")
	void dooo() throws JobParametersInvalidException, JobInstanceAlreadyExistsException, NoSuchJobException{
		jobOperator.start("helloWorldJob", String.valueOf(System.currentTimeMillis()));
	}
}
