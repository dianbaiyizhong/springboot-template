package com.zhenmei.wsc.rbac.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DefaultDs {
    private String url;
    private String username;
    private String password;
}
