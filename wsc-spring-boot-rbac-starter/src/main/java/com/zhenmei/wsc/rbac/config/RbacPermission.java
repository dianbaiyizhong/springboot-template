package com.zhenmei.wsc.rbac.config;

import lombok.Data;

import java.util.Objects;

@Data
public class RbacPermission {
    private String permissionName;

    public RbacPermission(String permissionName) {
        this.permissionName = permissionName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RbacPermission that = (RbacPermission) o;
        return Objects.equals(permissionName, that.permissionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionName);
    }

}
