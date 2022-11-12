package com.zhenmei.wsc.rbac.config;

import lombok.Data;

import java.util.Objects;

@Data
public class RbacRole {


    public RbacRole(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RbacRole rbacRole = (RbacRole) o;
        return Objects.equals(roleId, rbacRole.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }

    private Long roleId;






}
