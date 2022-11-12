package com.zhenmei.wsc.rbac.exception.handler;

import com.zhenmei.wsc.constant.RestCode;
import com.zhenmei.wsc.rbac.exception.AuthorizeException;
import com.zhenmei.wsc.rbac.exception.NotLoginException;
import com.zhenmei.wsc.response.ResultBuilder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
@ResponseBody
public class NotLoginExceptionHandler {


    @ExceptionHandler(value = NotLoginException.class)
    public Object handlerException(NotLoginException exception, HttpServletRequest request) {

        return ResultBuilder.error(exception,request, RestCode.NOT_LOGIN.getCode(),RestCode.NOT_LOGIN.getMessage());

    }
}
