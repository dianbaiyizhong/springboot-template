package com.zhenmei.wsc.rbac.config;

import com.google.common.collect.Sets;
import com.zhenmei.wsc.rbac.mybatis.custom.entity.PermissionEntity;
import com.zhenmei.wsc.rbac.mybatis.custom.mapper.PermissionDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Configuration
@Slf4j
public class RbacVerification {


    // 存储资源信息，用于快速检索判断
    private Map<RbacRole, Set<RbacPermission>> resMap = new HashMap<>();


    @Resource
    private PermissionDao permissionDao;


    @PostConstruct
    private void init() {
        List<PermissionEntity> permissionList = permissionDao.findAll();
        for (int i = 0; i < permissionList.size(); i++) {
            PermissionEntity entity = permissionList.get(i);
            for (int j = 0; j < entity.getRoleIds().size(); j++) {
                Long roleId = entity.getRoleIds().get(j);
                RbacRole rbacRole = new RbacRole(roleId);
                RbacPermission rbacPermission = new RbacPermission(entity.getPermissionName());
                loadResMap(rbacRole, rbacPermission);
            }
        }
        log.info("rbac load from database:{}", resMap);
    }

    private void loadResMap(RbacRole rbacRole, RbacPermission rbacPermission) {

        Set<RbacPermission> set = resMap.get(rbacRole);
        if (set == null) {
            resMap.put(rbacRole, Sets.newHashSet(rbacPermission));
        } else {
            set.add(rbacPermission);
        }

    }


    public boolean valid(Long roleId, String url) {
        Set<RbacPermission> rbacPermissions = resMap.get(new RbacRole(roleId));
        if (rbacPermissions.contains(url)) {
            return true;
        } else {
            return false;
        }
    }
}
