package com.silva.chetax.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.silva.chetax.rocketmq.beans.RocketMQChannelManager;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(RocketMQChannelManager.class)
public class RocketMQApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMQApplication.class, args);
    }

}