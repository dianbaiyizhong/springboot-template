package com.zhenmei.wsc.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleAuthorityForm  {
    private String requestUserId;

    private String selectRoles;
}
