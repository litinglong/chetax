package com.silva.chetax.schedule.center.common.response;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.silva.chetax.schedule.center.system.entity.SysTransInfoEntity;
import com.silva.chetax.schedule.center.system.service.ISysTransInfoService;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class ChetaxAopConfig {
	
	@Autowired
	private ISysTransInfoService iSysTransInfoService;
	
	@Pointcut("execution(public * com.silva.chetax.schedule.center.system.controller.SysScheduleInfoController.*(..)))")
	public void ChetaxBrokerAspect() {

	}

	@Around("ChetaxBrokerAspect()")
	public Object doAroundBusiness(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object[] args = proceedingJoinPoint.getArgs();
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
		if(sysTransInfo != null) {
			LocalDateTime now = LocalDateTime.now(ZoneId.of("+8"));
			sysTransInfo.setCreateTime(now);
			sysTransInfo.setUpdateTime(now);
			sysTransInfo.setTransStartTime(now);
			sysTransInfo.setCreateUser("-1");
			sysTransInfo.setUpdateUser("-1");
			sysTransInfo.setTransMethod(proceedingJoinPoint.getSignature().toString());
			sysTransInfo.setTransInput(JSON.toJSONString(args));
			if(baseIn != null) {
				sysTransInfo.setTransJnlNo(baseIn.getTransJnlNo());
			}
			iSysTransInfoService.save(sysTransInfo);
		}

		Object result = proceedingJoinPoint.proceed();
		if(sysTransInfo != null && sysTransInfo.getId() != null) {
			LocalDateTime now = LocalDateTime.now(ZoneId.of("+8"));
			sysTransInfo.setTransEndTime(now);
			sysTransInfo.setTransOutput(JSON.toJSONString(result));
			sysTransInfo.setUpdateTime(now);
			iSysTransInfoService.updateById(sysTransInfo);
		}
		return result;

	}
}
