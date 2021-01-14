# chetax
```puml
chetax
    chetax-cloud-component
    	chetax-admin-server ： 【容器监控管理服务器】
    	chetax-authorization-server ：【 身份认证服务器，基于oauth2.0】
    	chetax-gateway-server ： 【资源网关服务器，基于oauth2.0】
    chetax-cloud-service ：【 SpringCloud微服务子模块集】
        chetax-cloud-service1
            chetax-cloud-service1-api
            chetax-cloud-service1-impl：【RestTemplate式调用chetax-cloud-service2-api,含knife4j配置示例】
        chetax-cloud-service2
            chetax-cloud-service2-api
            chetax-cloud-service2-impl：【Feign式调用chetax-cloud-service1-api，含资源服务器配置】
        chetax-cloud-service3
            chetax-cloud-service3-api
            chetax-cloud-service3-impl：【Feign式调用chetax-cloud-service1-api】
        chetax-cloud-service-dubbo ： 【DUBBO2.7.4.1：注解方式调用chetax-dubbo-service4-api服务】
            chetax-cloud-service-dubbo-api
            chetax-cloud-service-dubbo-impl
    chetax-demo
        chetax-mq-demo：【MQ相关测试样例】
            chetax-mq-demo-kafka：【kafka相关测试样例】
            chetax-mq-demo-rocketmq：【rocketmq相关测试样例】
        chetax-oauth2-server-demo    
        chetax-spring-boot-starter-demo：【springboot启动器样例】
    chetax-dependecies-home：【外部依赖管理】
    	chetax-dependency-base
        chetax-dependency-dubbo
        chetax-dependency-dubbo-with-web：【开发springcloud项目】
        chetax-dependency-management：【jar包版本管理】
        chetax-dependency-web：【开发springcloud项目依赖此pom即可】
    chetax-dubbo-service ： 【DUBBO RPC服务子模块】
        chetax-dubbo-service1：【DUBBO2.5.3，XML配置方式】
            chetax-dubbo-service1-api
            chetax-dubbo-service1-impl
        chetax-dubbo-service2：【DUBBO2.5.3，XML配置方式】
            chetax-dubbo-service2-api
            chetax-dubbo-service2-impl：【调用chetax-dubbo-service1-api、chetax-dubbo-service3-api】
        chetax-dubbo-service3：【DUBBO2.7.4.1，注解方式】
            chetax-dubbo-service3-api
            chetax-dubbo-service3-impl
        chetax-dubbo-service4：【DUBBO2.7.4.1，注解方式】
            chetax-dubbo-service4-api
            chetax-dubbo-service4-impl：【调用chetax-dubbo-service3-api】
    chetax-sso
        chetax-sso-client
        chetax-sso-server
    chetax-test
        chetax-test-spring-life
    chetax-utils：【工具类工程】
    chetax-vue：【vue前端页面工程】  
```
