
package com.zhenmei.template.infrastructure.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessDataVO<T> {

    /**
     * 业务状态码
     */
    private int code;
    /**
     * 成功详情，展示给前端调用者
     */
    private String message;
    /**
     * 页面显示，展示给用户
     */
    private String clientTip;

    /**
     * 数据
     */
    private T data;


}
