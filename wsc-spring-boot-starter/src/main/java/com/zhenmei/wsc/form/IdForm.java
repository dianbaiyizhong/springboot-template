package com.zhenmei.wsc.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class IdForm {
    @NotNull
    private Long id;
}

