package com.zhenmei.wsc.exception.handler;


import com.zhenmei.wsc.constant.RestCode;
import com.zhenmei.wsc.response.ApiResponseDataBuilder;
import com.zhenmei.wsc.utils.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) {

        ResponseUtil.write(httpServletResponse, ApiResponseDataBuilder.error(e, httpServletRequest, RestCode.ACCESS_DENIED.getCode(), RestCode.ACCESS_DENIED.getMessage()));


    }
}
