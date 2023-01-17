package com.zhenmei.wsc.exception.handler;


import com.alibaba.fastjson2.JSON;
import com.zhenmei.wsc.constant.RestCode;
import com.zhenmei.wsc.response.ResultBuilder;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@ResponseBody
public class ValidExceptionHandler {
    @ExceptionHandler(value = BindException.class)
    public Object handlerException(BindException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResultBuilder.build(RestCode.PARAM_ERROR.getCode(), RestCode.PARAM_ERROR.getMessage(), JSON.toJSON(exception.getBindingResult().getFieldErrors())));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object handlerException(MethodArgumentNotValidException exception) {


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResultBuilder.build(RestCode.PARAM_ERROR.getCode(), RestCode.PARAM_ERROR.getMessage(), JSON.toJSON(exception.getBindingResult().getFieldErrors())));
    }


    @ExceptionHandler(value = ConstraintViolationException.class)
    public Object handlerException(ConstraintViolationException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResultBuilder.build(RestCode.PARAM_ERROR.getCode(), RestCode.PARAM_ERROR.getMessage(), JSON.toJSON(exception.getConstraintViolations())));

    }

}
