package com.zhenmei.wsc.rbac.pojo.vo;

import com.zhenmei.wsc.rbac.pojo.bo.RoleBo;
import lombok.Data;

import java.util.List;

@Data
public class LoginVo {


    private String token;
    private List<RoleBo> roleList;

}
