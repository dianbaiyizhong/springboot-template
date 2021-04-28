package com.zhenmei.exception;

import lombok.Data;

/**
 * 成功返回空值
 */
@Data
public class SuccessEmptyDataException extends RuntimeException{
    private transient Object data;

    public SuccessEmptyDataException(Object data) {
        this.data = data;
    }

    public SuccessEmptyDataException() {
    }
}
