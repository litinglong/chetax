# chetax
```puml
chetax
    chetax-cloud-component
    	chetax-admin-server ： 容器监控管理服务器
    	chetax-authorization-server ： 身份认证服务器，基于oauth2.0
    	chetax-gateway-server ： 资源网关服务器，基于oauth2.0
    chetax-cloud-service ： springcloud微服务子模块
        chetax-cloud-service1
            chetax-cloud-service1-api
            chetax-cloud-service1-impl
        chetax-cloud-service2
            chetax-cloud-service2-api
            chetax-cloud-service2-impl
        chetax-cloud-service3
            chetax-cloud-service3-api
            chetax-cloud-service3-impl
        chetax-cloud-service-dubbo ： 由dubbo提供业务服务的springcloud微服务子模块
            chetax-cloud-service-dubbo-api
            chetax-cloud-service-dubbo-impl
    chetax-demo
        chetax-mq-demo：MQ相关测试样例
            chetax-mq-demo-kafka：kafka相关测试样例
            chetax-mq-demo-rocketmq：rocketmq相关测试样例
        chetax-oauth2-server-demo    
        chetax-spring-boot-starter-demo：springboot启动器样例
    chetax-dependecies-home
        chetax-dependecies
    chetax-dubbo-service ： dubbo rpc服务子模块
        chetax-dubbo-service1
            chetax-dubbo-service1-api
            chetax-dubbo-service1-impl
        chetax-dubbo-service2
            chetax-dubbo-service2-api
            chetax-dubbo-service2-impl
        chetax-dubbo-service3
            chetax-dubbo-service3-api
            chetax-dubbo-service3-impl
        chetax-dubbo-service4
            chetax-dubbo-service4-api
            chetax-dubbo-service4-impl
    chetax-sso
        chetax-sso-client
        chetax-sso-server
    chetax-test
        chetax-test-spring-life
```
