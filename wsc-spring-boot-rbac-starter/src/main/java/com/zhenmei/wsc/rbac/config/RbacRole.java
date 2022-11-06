package com.zhenmei.wsc.rbac.config;

import lombok.Data;

import java.util.Objects;

@Data
public class RbacRole {

    public RbacRole(String roleName) {
        this.roleName = roleName;
    }

    private String roleName;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RbacRole rbacRole = (RbacRole) o;
        return Objects.equals(roleName, rbacRole.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }


}
