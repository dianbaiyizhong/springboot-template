package com.zhenmei.wsc.rbac.config;

import com.zhenmei.wsc.rbac.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 配置登录查看是否有token拦截器
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").order(0);

    }
}
