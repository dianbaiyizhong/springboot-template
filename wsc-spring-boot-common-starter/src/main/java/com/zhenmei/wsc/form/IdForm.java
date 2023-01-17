package com.zhenmei.wsc.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class IdForm {
    @NotNull
    private Long id;

    private String requestUserId;

}

