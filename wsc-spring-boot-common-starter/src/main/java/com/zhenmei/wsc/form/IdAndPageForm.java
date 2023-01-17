package com.zhenmei.wsc.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class IdAndPageForm extends BasePageForm {
    @NotNull
    private String id;
}

