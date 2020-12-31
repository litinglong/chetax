package com.test4;

import java.util.HashMap;
import java.util.Map;

import org.apache.dubbo.common.json.JSON;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

public class Main {
	public static void main(String[] args) {
		ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
		// 当前dubbo consumer的application配置，不设置会直接抛异常
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName("test4-service1");
		// 注册中心配置
		RegistryConfig registryConfig = new RegistryConfig();
		// 注册中心这里需要配置上注册中心协议，例如下面的zookeeper
		registryConfig.setAddress("nacos://127.0.0.1:8848");
		registryConfig.setGroup("test");
		reference.setApplication(applicationConfig);
		reference.setRegistry(registryConfig);
		// 设置调用的reference属性，下面只设置了协议、接口名、版本、超时时间
		reference.setProtocol("dubbo");
		reference.setInterface("com.test4.service.Test4Service");
		reference.setVersion("1.0.0");
		reference.setTimeout(10000);
		reference.setGroup("test");
		// 声明为泛化接口
		reference.setGeneric("true");
		// GenericService可以接住所有的实现
		GenericService genericService = reference.get();
		// 构造复杂参数，下面的示例中，头两个参数为string类型，后一个是一个复杂类型，但都可以通过map构造。
		Object result = genericService.$invoke("sayName",
				new String[] { "java.lang.String"},
				new Object[] { "123"});
		System.out.println(result);
		//System.out.println(JSON.toJSONString(result));
	}
}
