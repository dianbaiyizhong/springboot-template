package com.zhenmei.wsc.security.mybatis.custom.entity;

import lombok.Data;

import java.util.List;

@Data
public class PermissionEntity {
    /**
     * url
     */
    private String permissionUrl;

    /**
     * 角色名称
     */
    private List<String> roleNames;
}
