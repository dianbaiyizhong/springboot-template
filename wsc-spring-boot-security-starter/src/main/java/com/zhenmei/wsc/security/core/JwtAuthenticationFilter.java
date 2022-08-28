package com.zhenmei.wsc.security.core;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson.JSON;
import com.zhenmei.wsc.constant.RestCode;
import com.zhenmei.wsc.response.ResultBuilder;
import com.zhenmei.wsc.utils.ResponseUtil;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
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
import java.io.PrintWriter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(token)) {
            token = token.replace("Bearer ", "");
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            request.setAttribute("token", token);
            chain.doFilter(request, response);
            return;
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ResponseUtil.write(response, ResultBuilder.build(RestCode.NOT_LOGIN.getCode(),RestCode.NOT_LOGIN.getMessage()));
        }
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
