package com.zhenmei.wsc.security.core;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.zhenmei.wsc.security.mybatis.custom.mapper.RoleDao;
import com.zhenmei.wsc.security.mybatis.generate.entity.TAdminUser;
import com.zhenmei.wsc.security.mybatis.generate.mapper.TAdminUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private TAdminUserMapper userMapper;


    @Autowired
    private RoleDao roleDao;


    @Override
    public UserAuthInfo loadUserByUsername(String userName) {

        TAdminUser user = userMapper.selectOne(new QueryWrapper<TAdminUser>().lambda()
                .eq(TAdminUser::getUserName, userName)
        );
        if (user != null) {
            UserAuthInfo userAuthInfo = new UserAuthInfo();
            BeanUtils.copyProperties(user, userAuthInfo);
            List<String> roleList = roleDao.getRolesByUserId(user.getId());
            userAuthInfo.setRoles(roleList);
            return userAuthInfo;
        } else {
            throw new BadCredentialsException("admin: " + userName + " do not exist!");
        }
    }
}