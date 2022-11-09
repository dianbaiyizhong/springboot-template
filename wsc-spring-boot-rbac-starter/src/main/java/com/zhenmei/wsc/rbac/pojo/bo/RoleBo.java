package com.zhenmei.wsc.rbac.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RoleBo {
    private String roleId;
    private String roleName;
}
