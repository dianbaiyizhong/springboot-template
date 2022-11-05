package com.zm.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.zm.demo.mapper"})
public class MybatisTypeHandlerDemoApplication {
    public static void main(String[] args) {

        SpringApplication.run(MybatisTypeHandlerDemoApplication.class, args);

    }
}
