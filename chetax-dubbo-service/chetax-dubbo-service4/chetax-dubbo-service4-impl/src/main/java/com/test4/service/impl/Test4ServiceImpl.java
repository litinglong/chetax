package com.test4.service.impl;

import java.util.Date;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.rpc.RpcContext;
import com.test4.exception.CheckedException;
import com.test4.exception.UncheckedException;
import com.test4.service.Test4Service;

@Service(version = "1.0.0",group = "test")
public class Test4ServiceImpl implements Test4Service {

    @Value("${dubbo.application.name}")
    private String serviceName;

    public String sayName(String name) {
        RpcContext rpcContext = RpcContext.getContext();
        return String.format("Service [name :%s , port : %d] %s(\"%s\") : Hello,%s",
                this.serviceName,
                rpcContext.getLocalPort(),
                rpcContext.getMethodName(),
                name,
                name);
    }

	@Override
	public String checkedExceptionTest(String text) throws CheckedException {
		if("E".equals(text)) {
			throw new CheckedException();
		}
        return String.format("Service [name :%s, time :%s , text : %s]",
                this.serviceName,
                new Date().toString(),
                text);
	}

	@Override
	public String uncheckedExceptionTest(String text) throws UncheckedException {
		if("E".equals(text)) {
			throw new UncheckedException();
		}
		return String.format("Service [name :%s, time :%s , text : %s]",
                this.serviceName,
                new Date().toString(),
                text);
	}
}
