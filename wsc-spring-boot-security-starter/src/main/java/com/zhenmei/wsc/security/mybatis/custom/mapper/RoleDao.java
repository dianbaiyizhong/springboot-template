package com.zhenmei.wsc.security.mybatis.custom.mapper;

import java.util.List;

public interface RoleDao {

    List<String> getRolesByUserId(Long userId);

}
