package com.zhenmei.wsc.security.core;

import com.zhenmei.wsc.security.exception.AuthEntryPoint;
import com.zhenmei.wsc.security.exception.RestAuthenticationAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Autowired
    private MyUserDetailsService userDetailsService;


    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http.cors()
                .and().csrf().disable().authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/logout").permitAll()
                .anyRequest()
                .access("@RbacAuthorityService.hasPermission(request,authentication)")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new AuthEntryPoint())
                .accessDeniedHandler(new RestAuthenticationAccessDeniedHandler());
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();


    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }






    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }




    public static void main(String[] args) {

        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String hashPass = encode.encode("admin");

        System.out.println(hashPass);
    }


}