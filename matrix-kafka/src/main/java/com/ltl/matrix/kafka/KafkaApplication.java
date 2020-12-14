package com.ltl.matrix.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.ltl.matrix.kafka.beans.KafkaChannelManager;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(KafkaChannelManager.class)
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

}