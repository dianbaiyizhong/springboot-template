//package com.zhenmei.wsc.rbac.dao;
//
//import com.zhenmei.wsc.rbac.pojo.bo.PermissionEntity;
//import com.zm.utils.mysql.MysqlConnector;
//import com.zm.utils.mysql.ResultSetUtil;
//import org.springframework.stereotype.Repository;
//
//import javax.annotation.Resource;
//import java.sql.ResultSet;
//import java.util.List;
//
//@Repository
//public class PermissionDao {
//
//
//    @Resource
//    private MysqlConnector mysqlConnector;
//
//    public List findAll() {
//        ResultSet resultSet = mysqlConnector.query("SELECT " +
//                "        p.id, " +
//                "            p.url, " +
//                "            r.role_name " +
//                "        FROM " +
//                "            t_role_permission rp " +
//                "            LEFT JOIN t_role r on r.id = rp.role_id " +
//                "            LEFT JOIN t_permission p on p.id = rp.permission_id");
//        List<PermissionEntity> packed = ResultSetUtil.packed(resultSet, PermissionEntity.class);
//        return packed;
//    }
//}
