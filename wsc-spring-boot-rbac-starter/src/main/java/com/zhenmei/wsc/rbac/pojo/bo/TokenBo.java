package com.zhenmei.wsc.rbac.pojo.bo;

import lombok.Data;

import java.util.List;

@Data
public class TokenBo {
    private Long userId;
    private List<RoleBo> roleList;
}
