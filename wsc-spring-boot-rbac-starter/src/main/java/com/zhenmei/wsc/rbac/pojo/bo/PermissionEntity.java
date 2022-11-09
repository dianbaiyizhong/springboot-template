package com.zhenmei.wsc.rbac.pojo.bo;

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
