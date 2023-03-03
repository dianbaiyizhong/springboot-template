package com.zhenmei.wsc.rbac.config;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class RbacPermission {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RbacPermission that = (RbacPermission) o;
        return permissionName.equals(that.permissionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionName);
    }

    private String permissionName;

    public RbacPermission(String permissionName) {
        this.permissionName = permissionName;
    }



}
