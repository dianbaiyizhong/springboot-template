package com.zhenmei.wsc.security.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
