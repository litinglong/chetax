package com.silva.chetax.demo.spring.source.comments.component.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component // 表示一个带注释的类是一个“组件”，成为Spring管理的Bean。当使用基于注解的配置和类路径扫描时，这些类被视为自动检测的候选对象。同时@Component还是一个元注解。
@Aspect // 声明一个切面（就是说这是一个额外功能）
public class Test001AopBean {
    /**
     * 声明切点，即定义拦截规则，确定有哪些方法会被切入
     * 定义切入点，切入点为com.silva.chetax.demo.spring.source.comments.controller.AopController中的所有函数
     *通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * com.silva.chetax.demo.spring.source.comments.controller.TestController001.*(..)))")
    public void BrokerAspect(){
 
    }
 
    /**
     * 前置建言（advice），在原方法后执行。
    * @description  在连接点执行之前执行的通知
    */
    @Before("BrokerAspect()") // 属性值为被@Pointcut注解的方法名
    public void doBeforeGame(){
        System.out.println("经纪人正在处理球星赛前事务！");
    }
 
    /**
     * 后置建言（advice），在原方法前执行。
     * @description  在连接点执行之后执行的通知（返回通知和异常通知的异常）
     */
    @After("BrokerAspect()")
    public void doAfterGame(){
        System.out.println("经纪人为球星表现疯狂鼓掌！");
    }
    
    /**
     * 环绕建言（advice），在原方法执行前执行，在原方法执行后再执行（@Around可以实现其他两种advice）
     */
    @Around("BrokerAspect()")
    public void doAroundGame(){
        System.out.println("经纪人为球星表现疯狂鼓掌！");
    }
    /**
     * @description  在连接点执行之后执行的通知（返回通知）
     */
    @AfterReturning("BrokerAspect()")
    public void doAfterReturningGame(){
        System.out.println("返回通知：经纪人为球星表现疯狂鼓掌！");
    }
 
    /**
     * @description  在连接点执行之后执行的通知（异常通知）
     */
    @AfterThrowing("BrokerAspect()")
    public void doAfterThrowingGame(){
        System.out.println("异常通知：球迷要求退票！");
    }	
}
