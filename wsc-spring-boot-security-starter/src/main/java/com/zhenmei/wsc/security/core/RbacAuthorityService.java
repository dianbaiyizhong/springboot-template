package com.zhenmei.wsc.security.core;



import com.zhenmei.wsc.security.mybatis.custom.entity.PermissionEntity;
import com.zhenmei.wsc.security.mybatis.custom.mapper.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Component("RbacAuthorityService")
public class RbacAuthorityService {

    @Autowired
    private PermissionDao permissionDao;


    private HashMap<String, Collection<ConfigAttribute>> map = null;




    /**
     * 判断是否有权限
     *
     * @param request
     * @param authentication
     * @return
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Collection<ConfigAttribute> collection = getAttributes(request);
        if (authentication.getPrincipal().equals("anonymousUser")) {
            return false;
        }
        // 代表不需要验证，因为权限列表没有填入相关管控，因此放行
        if (null == collection || collection.size() <= 0) {
            return true;
        }

        ConfigAttribute configAttribute;
        String needRole;
        for (Iterator<ConfigAttribute> iterator = collection.iterator(); iterator.hasNext(); ) {
            configAttribute = iterator.next();
            needRole = configAttribute.getAttribute();
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                if (needRole.trim().equals(grantedAuthority.getAuthority())) {
                    return true;
                }
            }
        }
        throw new AccessDeniedException("权限不足");
    }

    /**
     * 判定用户请求的url是否在权限表中，如果在权限表中，则返回decide方法，
     * 用来判定用户是否有权限，如果不在权限表中则放行
     *
     * @param request
     * @return
     * @throws IllegalArgumentException
     */
    public Collection<ConfigAttribute> getAttributes(HttpServletRequest request) throws IllegalArgumentException {
        if (map == null) {
            map = new HashMap<>();
            loadResourceDefine();
        }
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : map.entrySet()) {
            String url = entry.getKey();
            if (new AntPathRequestMatcher(url).matches(request)) {
                return map.get(url);
            }
        }
        return null;
    }

    /**
     * 加载权限表中所有权限
     */
    private void loadResourceDefine() {
        List<PermissionEntity> all = permissionDao.findAll();
        for (PermissionEntity permissionDto : all) {
            List<ConfigAttribute> configAttributeList = permissionDto.getRoleNames().stream().map(roleName -> {
                ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + roleName);
                return configAttribute;
            }).collect(Collectors.toList());
            map.put(permissionDto.getPermissionUrl(), configAttributeList);
        }
    }


}
