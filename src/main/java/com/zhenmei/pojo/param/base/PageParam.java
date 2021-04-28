package com.zhenmei.pojo.param.base;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PageParam {
    private Integer page = 1;
    private Integer rows = 10;
}
