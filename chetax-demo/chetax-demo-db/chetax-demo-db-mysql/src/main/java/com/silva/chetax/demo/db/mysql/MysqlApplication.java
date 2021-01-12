package com.silva.chetax.demo.db.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.silva.chetax.demo.db.mysql")
@SpringBootApplication
public class MysqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(MysqlApplication.class, args);
    }
}
