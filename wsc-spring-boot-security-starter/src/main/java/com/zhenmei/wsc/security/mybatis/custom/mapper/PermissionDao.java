package com.zhenmei.wsc.security.mybatis.custom.mapper;


import com.zhenmei.wsc.security.mybatis.custom.entity.PermissionEntity;
import com.zhenmei.wsc.security.mybatis.generate.entity.TPermission;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface PermissionDao {

    @Select("SELECT\n" +
            "\tp.*\n" +
            "FROM\n" +
            "\tt_admin_user u\n" +
            "LEFT JOIN t_role_user ru ON u.id = ru.user_id\n" +
            "LEFT JOIN t_role r ON ru.role_id = r.id\n" +
            "LEFT JOIN t_role_permission rp ON rp.role_id = r.id\n" +
            "LEFT JOIN t_permission p ON p.id = rp.permission_id\n" +
            "WHERE\n" +
            "\tu.id = #{userId}")
    List<PermissionEntity> getByUserId(long userId);


    /**
     * 返回所有角色资源
     *
     * @return
     */
    List<PermissionEntity> findAll();


}
