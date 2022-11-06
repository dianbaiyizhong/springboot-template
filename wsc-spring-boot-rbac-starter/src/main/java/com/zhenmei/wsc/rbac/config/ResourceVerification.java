package com.zhenmei.wsc.rbac.config;

import com.zhenmei.wsc.rbac.dao.PermissionDao;
import com.zm.utils.mysql.MysqlConnector;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ResourceVerification {


    // 存储资源信息，用于快速检索判断
    private Map<RbacRole, Set<RbacPermission>> resMap = new HashMap<>();


    @Resource
    private PermissionDao permissionDao;

    @PostConstruct
    public void init() {
        permissionDao.findAll();

    }
}
