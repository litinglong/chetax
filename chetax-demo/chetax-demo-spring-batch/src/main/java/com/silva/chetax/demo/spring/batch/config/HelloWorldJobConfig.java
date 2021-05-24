package com.silva.chetax.demo.spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.silva.chetax.demo.spring.batch.model.Person;

@Configuration

public class HelloWorldJobConfig {

	private static final String jobName = "helloWorldJob";
	private static final String stepName = "helloWorldStep";
	@Autowired
	@Qualifier(stepName)
	private Step step;
	private static final String readerName = "personItemReader";
	private static final String writerName = "greetingItemWriter";

	@Bean
	public Job job(JobBuilderFactory jobBuilders, StepBuilderFactory stepBuilders) {
		return jobBuilders.get(jobName).start(step).build();
	}

	@Bean//(stepName)//支持服用用bean标签，不支持则调用方法
	public Step helloWorldStep(StepBuilderFactory stepBuilders) {
		return stepBuilders.get(stepName).<Person, String>chunk(10).reader(reader()).processor(processor())
				.writer(writer()).build();
	}
	@Bean
	public FlatFileItemReader<Person> reader() {
		return new FlatFileItemReaderBuilder<Person>().name(readerName)
				.resource(new ClassPathResource("csv/persons.csv")).delimited()
				.names(new String[] { "firstName", "lastName" }).targetType(Person.class).build();
	}
	@Bean
	public PersonItemProcessor processor() {
		return new PersonItemProcessor();
	}
	@Bean
	public FlatFileItemWriter<String> writer() {
		return new FlatFileItemWriterBuilder<String>().name(writerName)
				.resource(new FileSystemResource("target/test-outputs/greetings.txt"))
				.lineAggregator(new PassThroughLineAggregator<>()).build();
	}

}
