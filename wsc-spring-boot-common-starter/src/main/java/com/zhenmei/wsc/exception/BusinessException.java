package com.zhenmei.wsc.exception;

import com.zhenmei.wsc.constant.RestCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BusinessException extends RuntimeException {
    private transient Object data;

    private String clientTip;

    private String message;

    private RestCode restCode;


}
