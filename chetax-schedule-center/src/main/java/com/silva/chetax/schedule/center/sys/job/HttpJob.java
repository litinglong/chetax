package com.silva.chetax.schedule.center.sys.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.silva.chetax.schedule.center.sys.entity.ScheduleInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpJob extends QuartzJobBean {
    
	@Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		ScheduleInfo httpJobEntity = (ScheduleInfo)jobExecutionContext.getMergedJobDataMap().get("scheduleInfo");
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> userInfo = Maps.newHashMap();
    
//        userInfo.put("phone", accountForm.getPhone());
//        
//        userInfo.put("job", accountForm.getJob());
//        userInfo.put("email", accountForm.getEmail());
       
 
//        Map<String, Object> postBody = Maps.newHashMap();
//        postBody.put("userInfo", userInfo);

        HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		 
//		headers.add("basecret", config.getBasecret());
//		headers.add("baid", config.getBaid());
//		 
//		List<String> cookies = new ArrayList<>();
//		cookies.add("COOKIE_USER" + Strings.nullToEmpty(config.getCookie()));
//		headers.put(HttpHeaders.COOKIE, cookies);
        HttpEntity<String> requestEntity = new HttpEntity<String>(httpJobEntity.getRequestbody(), headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(httpJobEntity.getUrl(), requestEntity, String.class, "");
        log.info("url >> {} >> {}", httpJobEntity.getUrl(), responseEntity.getBody());
    }
}

//RestTemplate restTemplate = new RestTemplate();
//String url = "http://47.xxx.xxx.96/register/checkEmail";
//HttpHeaders headers = new HttpHeaders();
//headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
//map.add("email", "844072586@qq.com");
//
//HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
//System.out.println(response.getBody());
