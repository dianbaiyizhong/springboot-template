package com.zhenmei.wsc.rbac.service.impl;

import com.zhenmei.wsc.rbac.mybatis.generate.entity.TPermission;
import com.zhenmei.wsc.rbac.mybatis.generate.mapper.TPermissionMapper;
import com.zhenmei.wsc.rbac.service.AdminMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class AdminMenuServiceImpl implements AdminMenuService {


    @Resource
    private TPermissionMapper permissionMapper;


    @Override
    public List<Map<String, Object>> list() {
        List<TPermission> permissionList = permissionMapper.selectList(null);
        return findChildren(0l, permissionList);
    }


    private static List<Map<String, Object>> findChildren(Long id, List<TPermission> permissionList) {
        List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
        for (TPermission permission : permissionList) {
            if (permission.getPid().equals(id)) {
                Map<String, Object> menuChildMap = new HashMap<>();
                menuChildMap.put("id", permission.getId());
                menuChildMap.put("pid", permission.getPid());
                menuChildMap.put("path", permission.getPath());
                menuChildMap.put("name", permission.getName());
                menuChildMap.put("component", permission.getComponent());
                menuChildMap.put("createTime", permission.getCreatedAt());
                menuChildMap.put("orderNo", permission.getOrderNo());
                menuChildMap.put("status", permission.getStatus());
                menuChildMap.put("type", permission.getType());
                menuChildMap.put("icon", permission.getIcon());
                menuChildMap.put("showFlag", permission.getShowFlag());
                menuChildMap.put("title", permission.getTitle());
                menuChildMap.put("requestUrl", permission.getRequestUrl());
                menuChildMap.put("requestMethod", permission.getRequestMethod());

                Map<String, Object> metaChildMap = new HashMap<>();
                metaChildMap.put("title", permission.getTitle());
                metaChildMap.put("icon", permission.getIcon());

                menuChildMap.put("meta", metaChildMap);
                menuChildMap.put("children", findChildren(permission.getId(), permissionList));
                children.add(menuChildMap);
            }
        }
        // 根据orderNo排序 https://www.cnblogs.com/mr-wuxiansheng/p/7768491.html
        Collections.sort(children, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Integer name1 = Integer.valueOf(o1.get("orderNo").toString());//name1是从你list里面拿出来的一个
                Integer name2 = Integer.valueOf(o2.get("orderNo").toString()); //name1是从你list里面拿出来的第二个name
                return name1.compareTo(name2);
            }
        });

        return children;
    }


}
