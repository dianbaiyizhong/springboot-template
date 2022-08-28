package com.zhenmei.wsc.security.mybatis.custom.mapper;


import com.zhenmei.wsc.security.mybatis.custom.entity.PermissionEntity;

import java.util.List;


public interface PermissionDao {


    /**
     * 返回所有角色资源，若资源没有绑定角色，则放通行
     *
     * @return
     */
    List<PermissionEntity> findAll();


}
