package com.zhenmei.wsc.exception.handler;

import com.zhenmei.wsc.constant.RestCode;
import com.zhenmei.wsc.exception.AuthorizeException;
import com.zhenmei.wsc.response.ApiResponseDataBuilder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
@ResponseBody
public class AuthorizeExceptionHandler {


    // 拦截登录异常
    @ExceptionHandler(value = AuthorizeException.class)
    public Object handlerException(AuthorizeException exception, HttpServletRequest request) {

        return ApiResponseDataBuilder.error(exception,request, RestCode.LOGIN_ERROR.getCode(),RestCode.LOGIN_ERROR.getMessage());

    }
}
