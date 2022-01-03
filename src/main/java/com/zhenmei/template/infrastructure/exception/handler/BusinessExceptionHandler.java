package com.zhenmei.template.infrastructure.exception.handler;

import com.zhenmei.template.infrastructure.exception.BusinessException;
import com.zhenmei.template.infrastructure.response.ApiResponseDataBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class BusinessExceptionHandler {
    @ExceptionHandler({BusinessException.class})
    public Object handlerException(BusinessException exception, HttpServletRequest request) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseDataBuilder.success(exception));

    }

}
