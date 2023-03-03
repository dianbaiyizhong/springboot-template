//package com.zhenmei.wsc.demo;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import javax.sql.DataSource;
//
//@Configuration
//
//public class DataSourceConfig {
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public DataSource getDataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(env.getProperty("spring.datasource.druid.url"));
//        dataSource.setUsername(env.getProperty("spring.datasource.druid.username"));
//        dataSource.setPassword(env.getProperty("spring.datasource.druid.password"));
//        dataSource.setMaxWait(60000);
//        dataSource.setMaxActive(20);
//        return dataSource;
//    }
//}
