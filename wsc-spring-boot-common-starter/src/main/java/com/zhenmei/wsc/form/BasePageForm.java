package com.zhenmei.wsc.form;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestAttribute;

import javax.validation.constraints.NotNull;

@Data
public class BasePageForm {
    @NotNull
    private Integer page;
    @NotNull
    private Integer rows;


    private String requestUserId;
}
