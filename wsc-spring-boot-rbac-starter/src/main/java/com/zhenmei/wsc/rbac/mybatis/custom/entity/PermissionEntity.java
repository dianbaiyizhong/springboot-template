package com.zhenmei.wsc.rbac.mybatis.custom.entity;

import lombok.Data;

import java.util.List;

@Data
public class PermissionEntity {
    /**
     * url
     */
    private String permissionUrl;

    private String permissionName;

    private List<Long> roleIds;

    /**
     * 角色名称
     */
    private List<String> roleNames;
}
