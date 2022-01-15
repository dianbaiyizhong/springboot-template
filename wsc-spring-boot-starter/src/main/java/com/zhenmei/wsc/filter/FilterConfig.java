package com.zhenmei.wsc.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registerSleepFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SleepFilter());
        registration.addUrlPatterns("/*");
        registration.setName("SleepFilter");
        registration.setOrder(1);
        return registration;
    }

}
