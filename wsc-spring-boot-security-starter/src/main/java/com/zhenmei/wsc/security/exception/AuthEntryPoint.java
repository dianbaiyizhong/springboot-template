package com.zhenmei.wsc.security.exception;

import com.zhenmei.wsc.constant.RestCode;
import com.zhenmei.wsc.response.ResultBuilder;
import com.zhenmei.wsc.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ResponseUtil.write(response, ResultBuilder.build(RestCode.NOT_LOGIN.getCode(),RestCode.NOT_LOGIN.getMessage()));
    }
}