package com.silva.chetax.service3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.silva.chetax.service3.serviceclient.fallback.Service1clientFallback;

@Configuration
public class FeignConfiguration {
    @Bean
    public Service1clientFallback service1clientFallback() {
        return new Service1clientFallback();
    }
}
