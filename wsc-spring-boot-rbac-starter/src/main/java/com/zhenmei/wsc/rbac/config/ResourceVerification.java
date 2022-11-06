package com.zhenmei.wsc.rbac.config;

import cn.hutool.core.thread.ThreadUtil;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusLanguageDriverAutoConfiguration;
import com.zhenmei.wsc.rbac.mybatis.custom.mapper.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration
public class ResourceVerification {


    // 存储资源信息，用于快速检索判断
    private Map<RbacRole, Set<RbacPermission>> resMap = new HashMap<>();


    @Autowired
    private PermissionDao permissionDao;




    @PostConstruct
    public void init() {


        List permissionList = permissionDao.findAll();

        System.out.println(permissionList);
    }


    public boolean valid(String role, String url) {
        Set<RbacPermission> rbacPermissions = resMap.get(new RbacRole(role));
        if (rbacPermissions.contains(url)) {
            return true;
        } else {
            return false;
        }
    }
}
