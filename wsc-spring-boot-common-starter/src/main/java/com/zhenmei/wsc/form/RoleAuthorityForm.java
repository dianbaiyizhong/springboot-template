package com.zhenmei.wsc.form;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class RoleAuthorityForm  {
    private String requestUserId;

    private String selectRoles;
}
