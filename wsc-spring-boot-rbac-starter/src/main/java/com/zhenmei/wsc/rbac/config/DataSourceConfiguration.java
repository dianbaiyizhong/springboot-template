package com.zhenmei.wsc.rbac.config;

import com.zm.utils.mysql.MysqlConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@Configuration
@Deprecated
public class DataSourceConfiguration {


    @Resource
    private Environment environment;

    @Resource
    private DefaultDs defaultDs;

    @Bean
    public MysqlConnector mysqlConnector() {

        String isUseDefaultDs = environment.getProperty("wsc.rbac.use-default-ds");
        if (isUseDefaultDs == null || "false".equals(isUseDefaultDs)) {
            String url = environment.getProperty("wsc.rbac.url");
            String username = environment.getProperty("wsc.rbac.username");
            String password = environment.getProperty("wsc.rbac.password");
            return new MysqlConnector(url, username, password);
        }
        return new MysqlConnector(defaultDs.getUrl(), defaultDs.getUsername(), defaultDs.getPassword());
    }


}