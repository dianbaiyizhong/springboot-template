package com.zhenmei.wsc.constant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestCode {
    public static final RestCode SUCCESS = new RestCode(0, "success");
    public static final RestCode PARAM_ERROR = new RestCode(400001, "参数错误");


    public static final RestCode UN_KNOW_ERROR = new RestCode(500000, "未知错误");


    public static final RestCode CODE_409001 = new RestCode(409001);
    public static final RestCode ALREADY_GET = new RestCode(409001, "不允许重复执行操作");

    public static final RestCode NOT_FOUND_RECORD = new RestCode(404001, "查无此记录...");

    public static final RestCode ALREADY_GET_TICKET = new RestCode(409001, "不允许重复执行操作","你已抢到该站火车票");



    public RestCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestCode(int code) {
        this.code = code;
    }

    private int code;
    private String message;
    private String clientInfo;
}
