package com.zhenmei.wsc.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class BatchDeleteForm {

    private String requestUserId;

    @NotBlank
    private String ids;
}
