package com.zhenmei.wsc.rbac.entity;

import lombok.Data;


@Data
public class PermissionEntity {

    private Long id;

    /**
     * url
     */
    private String url;

    /**
     * 角色名称
     */
    private String roleName;
}
