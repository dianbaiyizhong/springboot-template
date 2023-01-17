package com.zhenmei.wsc.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestAttribute;


@Data
public class BasePageForm {
    @NotNull
    private Integer page;
    @NotNull
    private Integer rows;


    private String requestUserId;
}
