package com.zhenmei.wsc.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class IdAndPageForm extends BasePageForm {
    @NotNull
    private String id;
}

