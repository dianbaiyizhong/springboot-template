package com.zhenmei.wsc.response;

import com.zhenmei.wsc.exception.BusinessException;
import com.zhenmei.wsc.constant.RestCode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

/**
 * http body的data的构造类
 */
@Slf4j
public class ResultBuilder {
    private static final String B3_TRACE_ID = "X-B3-TraceId";

    private ResultBuilder() {
    }

    /**
     * 返回一个简单的成功对象
     *
     * @return
     */
    public static ResultDataVO success() {
        return ResultDataVO.builder().code(RestCode.SUCCESS.getCode()).message(RestCode.SUCCESS.getMessage()).build();
    }

    /**
     * 返回一个实体类
     *
     * @param data
     * @return
     */
    public static ResultDataVO success(Object data) {

        return ResultDataVO.builder().code(RestCode.SUCCESS.getCode()).message(RestCode.SUCCESS.getMessage()).data(data).build();

    }

    public static ResultDataVO success(BusinessException businessException) {

        String clientInfo = Optional.ofNullable(businessException.getRestCode().getClientInfo()).orElse(businessException.getClientTip());
        String message = Optional.ofNullable(businessException.getRestCode().getMessage()).orElse(businessException.getMessage());
        Integer code = businessException.getRestCode().getCode();

        return ResultDataVO.builder()
                .code(code)
                .message(message)
                .clientTip(clientInfo)
                .build();
    }

    /**
     * 返回一个错误的实体类
     *
     * @param exception
     * @param request
     * @param code
     * @param message
     * @return
     */
    public static ResultDataVO error(Exception exception, HttpServletRequest request, int code, String message) {
        log.error("[Error occurred.] - [code={}, message={}] ", code, exception.getMessage());
        log.error("print stack:", exception);
        return ResultDataVO.builder()
                .code(code)
                .message(message)
                .data(exception.getMessage())
                .traceId(MDC.get(B3_TRACE_ID))
                .timestamp(new Date())
                .path(request.getRequestURI())
                .build();
    }


}
