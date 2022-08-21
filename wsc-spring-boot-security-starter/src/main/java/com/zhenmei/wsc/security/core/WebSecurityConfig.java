package com.zhenmei.wsc.security.core;
import com.zhenmei.wsc.security.exception.AuthEntryPoint;
import com.zhenmei.wsc.security.exception.RestAuthenticationAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private MyUserDetailsService userDetailsService;


    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
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
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    public static void main(String[] args) {

        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String hashPass = encode.encode("admin");

        System.out.println(hashPass);
    }


}