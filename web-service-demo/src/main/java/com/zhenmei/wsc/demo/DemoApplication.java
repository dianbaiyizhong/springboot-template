package com.zhenmei.wsc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Date;

@EnableAsync
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})

public class DemoApplication {
    public static void main(String[] args) {
        System.out.println(new Date());

        SpringApplication.run(DemoApplication.class, args);

    }
}
