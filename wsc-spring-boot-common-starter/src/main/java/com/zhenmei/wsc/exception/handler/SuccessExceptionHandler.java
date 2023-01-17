package com.zhenmei.wsc.exception.handler;

import com.zhenmei.wsc.exception.SuccessEmptyDataException;
import com.zhenmei.wsc.exception.SuccessException;
import com.zhenmei.wsc.response.ResultBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@ResponseBody
public class SuccessExceptionHandler {
    @ExceptionHandler({SuccessException.class, SuccessEmptyDataException.class})
    public Object handlerException(Exception exception, HttpServletRequest request) {

        if (exception instanceof SuccessException) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResultBuilder.success(((SuccessException) exception).getData()));
        } else if (exception instanceof SuccessEmptyDataException) {
            // 若项目组要求严格按照HTTP请求状态码返回，则可以单独处理
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ResultBuilder.success(((SuccessEmptyDataException) exception).getData()));
        }

        return null;
    }

}
