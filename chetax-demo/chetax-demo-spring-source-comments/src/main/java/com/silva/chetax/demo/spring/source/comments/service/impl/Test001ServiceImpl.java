package com.silva.chetax.demo.spring.source.comments.service.impl;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.silva.chetax.demo.spring.source.comments.dao.Test001Dao;
import com.silva.chetax.demo.spring.source.comments.service.Test001Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service // 业务层，一般对于接口和实现
public class Test001ServiceImpl implements Test001Service{
	/**
	 * 
	 * 说明：
	 * <p>共同点：@Resource和@Autowired都可以作为注入属性的修饰，在接口仅有单一实现类时，两个注解的修饰效果相同，可以互相替换，不影响使用。
	 * <p>不同点：
	 * <p>@Resource是Java自己的注解，@Resource有两个属性是比较重要的，分是name和type；Spring将@Resource注解的name属性解析为bean的名字，而type属性则解析为bean的类型。所以如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略。如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。
	 * <p>@Autowired是spring的注解，是spring2.5版本引入的，Autowired只根据type进行注入，不会去匹配name。如果涉及到type无法辨别注入对象时，那需要依赖@Qualifier或@Primary注解一起来修饰。
	 */
	@Resource(name = "test001Dao") //bean的注入，同Autowired 有相同的功能。
	Test001Dao test001Dao;
	
	// 由@EnableScheduling注解进行支持
	@Scheduled(fixedDelay = 50000) //以下的方法将以一个固定延迟时间50s调用一次执行，这个周期是以上一个调用任务的完成时间为基准，在上一个任务完成之后，50s后再次执行
//	@Scheduled(fixedRate = 50000) //以下方法将以一个固定速率50s来调用一次执行，这个周期是以上一个任务开始时间为基准，从上一任务开始执行后50s再次调用：
//	@Scheduled(cron = "0 0 2 * * ?") //每天凌晨两点执行
	@Override
	public void test001() {
		log.info("--------start-service1------------");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 模拟耗时
	    log.info("--------end-service1------------");
	}

	@Async("taskExecutor") //ThreadPoolTaskConfig#getAsyncExecutor()
	@Override
	public String test002() throws InterruptedException {
		log.info("--------start-test002------------");
		Thread.sleep(5000); // 模拟耗时
	    log.info("--------end-test002------------");
		return null;
	}
	
	@Async("taskExecutor") //ThreadPoolTaskConfig#getAsyncExecutor()
	public String test003() throws InterruptedException {
		log.info("--------start-test003------------");
		Thread.sleep(2000); // 模拟耗时
	    log.info("--------end-test003------------");
		return null;
	}

}
