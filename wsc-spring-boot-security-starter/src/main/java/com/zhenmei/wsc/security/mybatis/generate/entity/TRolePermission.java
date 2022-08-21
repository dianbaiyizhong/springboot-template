package com.zhenmei.wsc.security.mybatis.generate.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author nntk
 * @since 2022-08-21
 */
@TableName("t_role_permission")
public class TRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long permissionId;

    private Long roleId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "TRolePermission{" +
        "id=" + id +
        ", permissionId=" + permissionId +
        ", roleId=" + roleId +
        "}";
    }
}
