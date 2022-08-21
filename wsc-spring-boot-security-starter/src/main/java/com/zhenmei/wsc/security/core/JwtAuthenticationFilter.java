package com.zhenmei.wsc.security.core;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        /**
         * 这里为了配合vue-admin的前端方式，获取X-TOKEN
         */
        String token = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(token)) {
            token = token.replace("Bearer ", "");
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            request.setAttribute("token", token);
            chain.doFilter(request, response);
            return;
        }
        chain.doFilter(request, response);
    }

    /**
     * 这里从token中获取用户信息
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        UserAuthInfo principal = JSON.parseObject(jwt.getPayloads().toString(), UserAuthInfo.class);
        if (principal != null) {
            return new UsernamePasswordAuthenticationToken(principal.getId(), principal.getPassword(), principal.getAuthorities());
        }
        return null;
    }
}
