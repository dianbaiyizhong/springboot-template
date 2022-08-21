package com.zhenmei.ws;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.sql.SQLException;


@EnableAsync
@SpringBootApplication
public class TemplateServerApplication {


    public static void main(String[] args) {

        SpringApplication.run(TemplateServerApplication.class, args);

    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
}
