package com.zhenmei.wsc.rbac.pojo.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class LoginForm {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
