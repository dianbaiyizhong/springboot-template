package com.zm.wsc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MultiDataSourceDemoApplication {
    public static void main(String[] args) {

        SpringApplication.run(MultiDataSourceDemoApplication.class, args);

    }
}
