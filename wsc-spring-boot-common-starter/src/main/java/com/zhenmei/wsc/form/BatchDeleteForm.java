package com.zhenmei.wsc.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BatchDeleteForm {

    private String requestUserId;

    @NotBlank
    private String ids;
}
