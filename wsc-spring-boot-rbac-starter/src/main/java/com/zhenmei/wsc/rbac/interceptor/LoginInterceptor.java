package com.zhenmei.wsc.rbac.interceptor;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson2.JSON;
import com.zhenmei.wsc.rbac.exception.NotLoginException;
import com.zhenmei.wsc.rbac.pojo.bo.TokenBo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");

        if (StringUtils.isEmpty(token)) {
            throw new NotLoginException("当前未登录");
        } else {
            JWT jwt = JWTUtil.parseToken(token);
            TokenBo principal = JSON.parseObject(jwt.getPayloads().toString(), TokenBo.class);
            request.setAttribute("requestUserId",principal.getUserId());
        }
        return true;

    }

}
