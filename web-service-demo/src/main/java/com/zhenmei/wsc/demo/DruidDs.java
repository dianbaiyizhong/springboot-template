package com.zhenmei.wsc.demo;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class DruidDs {

    public static String password = "root";

    @Bean
    public DruidDataSource druid() throws Exception {
//        InputStream is = DruidDs.class.getResourceAsStream("/druid.properties");
//        Properties pp = new Properties();
//        pp.load(is);
//        // 创建连接池，使用配置文件中的参数
//        DataSource ds = DruidDataSourceFactory.createDataSource(pp);


        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername("root");
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost/wsc_admin?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&useTimezone=true&allowPublicKeyRetrieval=true&socketTimeout=60000");
//        druidDataSource.setTimeBetweenEvictionRunsMillis(1000);
        return druidDataSource;
    }


}
