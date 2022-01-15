package com.zhenmei.wsc.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;


@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDataVO {
    private int code;
    private String message;
    private Object data;
    private String traceId;
    private Date timestamp;
    private String path;
}
