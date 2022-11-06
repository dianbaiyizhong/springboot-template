package com.zhenmei.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class RbacDemoApplication {
    public static void main(String[] args) {

        SpringApplication.run(RbacDemoApplication.class, args);

    }
}
