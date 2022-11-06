package com.zhenmei.wsc.rbac.mybatis.custom.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhenmei.wsc.rbac.mybatis.custom.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {

    List<String> getRolesByUserId(Long userId);


    @Select(
            "SELECT r.role_name, r.id AS role_id  FROM t_user_role tur LEFT JOIN t_role r ON r.id = tur.role_id " +
                    " ${ew.customSqlSegment}")
    Page<UserRoleEntity> getRoleByUserId(@Param("ew") Wrapper ew, Page page);

}
