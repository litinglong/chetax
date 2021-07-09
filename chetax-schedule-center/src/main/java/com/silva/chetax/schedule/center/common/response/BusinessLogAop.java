package com.silva.chetax.schedule.center.common.response;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.silva.chetax.schedule.center.system.entity.SysTransInfoEntity;
import com.silva.chetax.schedule.center.system.service.ISysTransInfoService;
import com.silva.chetax.schedule.center.utils.ExceptionUtils;
import com.silva.chetax.schedule.center.utils.TimeUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class BusinessLogAop {
	
	@Autowired
	private ISysTransInfoService iSysTransInfoService;
	
	@Around("@annotation(com.silva.chetax.schedule.center.common.response.BusinessLog)")
	public Object doAroundBusiness(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        BusinessLog businessLog = method.getAnnotation(BusinessLog.class);
        if(businessLog == null) {
        	return joinPoint.proceed();
        }
		Object[] args = joinPoint.getArgs();
		SysTransInfoEntity sysTransInfo = null;
		BaseIn baseIn = null;
		for (Object arg : args) {
			if (sysTransInfo == null && arg instanceof SysTransInfoEntity) {
				sysTransInfo = (SysTransInfoEntity) arg;
			}
			if (baseIn == null && arg instanceof BaseIn) {
				baseIn = (BaseIn) arg;
			}
			if(sysTransInfo != null && baseIn != null) {
				break;
			}
		}
		LocalDateTime start = LocalDateTime.now(ZoneId.of("+8"));
		if(sysTransInfo != null) {
			sysTransInfo.setCreateTime(start);
			sysTransInfo.setTransStartTime(start);
			sysTransInfo.setCreateUser("-1");
			sysTransInfo.setTransMethod(joinPoint.getSignature().toString());
			sysTransInfo.setTransInput(JSON.toJSONString(args));
			if(baseIn != null) {
				sysTransInfo.setTransJnlNo(baseIn.getTransJnlNo());
			}
			iSysTransInfoService.save(sysTransInfo);
		}
		try {
			Object result = joinPoint.proceed();
			if(result != null) {
				sysTransInfo.setTransOutput(JSON.toJSONString(result));
			}
			return result;
		} catch (Exception e) {
			if(sysTransInfo != null) {
				sysTransInfo.setTransException(StringUtils.substring(ExceptionUtils.stackTraceToString(e), 0, 10000));
			}
			throw e;
		} finally {
			if(sysTransInfo != null && sysTransInfo.getId() != null) {
				LocalDateTime end = LocalDateTime.now(ZoneId.of("+8"));
				sysTransInfo.setTransEndTime(end);
				sysTransInfo.setUpdateTime(end);
				sysTransInfo.setUpdateUser("-1");
				Long transUsedTime = TimeUtils.usedTime(start, end);
				String transUsedTimeFormated = TimeUtils.formateMs(transUsedTime);
				sysTransInfo.setTransUsedTimeFormated(transUsedTimeFormated);
				sysTransInfo.setTransUsedTime(transUsedTime);
				iSysTransInfoService.updateById(sysTransInfo);
			}
		}

	}
}
