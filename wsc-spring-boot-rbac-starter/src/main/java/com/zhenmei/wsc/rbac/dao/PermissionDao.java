package com.zhenmei.wsc.rbac.dao;

import com.zhenmei.wsc.rbac.entity.PermissionEntity;
import com.zm.utils.mysql.MysqlConnector;
import com.zm.utils.mysql.ResultSetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PermissionDao {


    @Autowired
    private MysqlConnector mysqlConnector;

    public List findAll() {
        ResultSet resultSet = mysqlConnector.query("SELECT\n" +
                "        p.id,\n" +
                "            p.url,\n" +
                "            r.role_name\n" +
                "        FROM\n" +
                "            t_role_permission rp\n" +
                "            LEFT JOIN t_role r on r.id = rp.role_id\n" +
                "            LEFT JOIN t_permission p on p.id = rp.permission_id");
        List<PermissionEntity> packed = ResultSetUtil.packed(resultSet, PermissionEntity.class);
        System.out.println(packed);
        return null;
    }
}
