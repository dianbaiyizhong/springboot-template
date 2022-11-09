package com.zhenmei.wsc.rbac.service.impl;

import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhenmei.wsc.exception.AuthorizeException;
import com.zhenmei.wsc.rbac.mybatis.custom.entity.UserRoleEntity;
import com.zhenmei.wsc.rbac.mybatis.custom.mapper.RoleDao;
import com.zhenmei.wsc.rbac.mybatis.generate.entity.TAdminUser;
import com.zhenmei.wsc.rbac.mybatis.generate.mapper.TAdminUserMapper;
import com.zhenmei.wsc.rbac.pojo.bo.RoleBo;
import com.zhenmei.wsc.rbac.pojo.form.LoginForm;
import com.zhenmei.wsc.rbac.pojo.vo.LoginVo;
import com.zhenmei.wsc.rbac.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Resource
    private TAdminUserMapper adminUserMapper;

    @Resource
    private RoleDao roleDao;

    @Override
    public LoginVo login(LoginForm loginForm) {


        String userName = loginForm.getUsername();
        TAdminUser user = adminUserMapper.selectOne(new QueryWrapper<TAdminUser>().lambda()
                .eq(TAdminUser::getUserName, userName)
        );
        if (user != null) {

            LoginVo loginVo = new LoginVo();
            List<UserRoleEntity> roleList = roleDao.getRolesByUserId(user.getId());

            loginVo.setRoleList(roleList.stream().map((Function<UserRoleEntity, RoleBo>) userRoleEntity -> null).collect(Collectors.toList()));
            Map<String, Object> tokenMap = new HashMap<>();
            tokenMap.put("userId", user.getUserName());
            String token = JWTUtil.createToken(tokenMap, "salt?".getBytes());
            loginVo.setToken(token);
            return loginVo;
        } else {
            throw new AuthorizeException("无效的用户名或密码");
        }


    }


}
