package com.silva.chetax.service3.serviceclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("chetax-service1")
public interface Service1client {
	@RequestMapping(method = RequestMethod.GET, value = "/echo/{message}?access_token=7a96eaac-f84f-4407-badd-ec662d9e4a56")
    public String echo(@PathVariable(value="message") String message);
}
