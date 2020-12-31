package com.test3.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.rpc.RpcContext;
import com.test3.service.Test3Service;

@Service(version = "1.0.0",group = "test")
public class Test3ServiceImpl implements Test3Service {

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
}
