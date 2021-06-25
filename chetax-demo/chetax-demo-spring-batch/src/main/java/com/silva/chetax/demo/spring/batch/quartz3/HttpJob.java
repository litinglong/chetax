package com.silva.chetax.demo.spring.batch.quartz3;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpJob extends QuartzJobBean {
    
	@Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        HttpJobEntity httpJobEntity = (HttpJobEntity)jobExecutionContext.getMergedJobDataMap().get("HttpJobEntity");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(httpJobEntity.getUrl(), String.class);
        log.info("url >> {}", httpJobEntity.getUrl(), responseEntity.getBody());
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
