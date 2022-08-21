package com.zhenmei.wsc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorizeException extends RuntimeException {

    private String message;

}

