package com.zhenmei.wsc.exception.handler;


import com.alibaba.fastjson.JSON;
import com.zhenmei.wsc.response.ApiResponseDataBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@ResponseBody
public class ValidExceptionHandler {
    @ExceptionHandler(value = BindException.class)
    public Object handlerException(BindException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseDataBuilder.success(JSON.toJSONString(exception.getBindingResult().getFieldErrors())));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object handlerException(MethodArgumentNotValidException exception) {


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseDataBuilder.success(JSON.toJSONString(exception.getBindingResult().getFieldErrors())));
    }


    @ExceptionHandler(value = ConstraintViolationException.class)
    public Object handlerException(ConstraintViolationException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseDataBuilder.success(exception.getConstraintViolations().toString()));
    }

}
