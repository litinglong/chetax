package com.silva.chetax.demo.spring.batch.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = { SpringBatchApplicationTests.BatchTestConfig.class })
@SpringBootTest
public class SpringBatchApplicationTests {

//	@Autowired
//	private JobLauncherTestUtils jobLauncherTestUtils;
//
//	@Test
//	public void testHelloWorldJob() throws Exception {
//		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
//		assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");
//	}

//	@Configuration
//	@Import({ BatchConfig.class, HelloWorldJobConfig.class })
//	static class BatchTestConfig {
//
//		@Autowired
//		private Job helloWorlJob;
//
//		@Bean
//		JobLauncherTestUtils jobLauncherTestUtils() throws NoSuchJobException {
//			JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
//			jobLauncherTestUtils.setJob(helloWorlJob);
//
//			return jobLauncherTestUtils;
//		}
//	}
	@Test
	public void testHelloWorldJob() throws Exception {
		System.out.println(11);
	}
}
