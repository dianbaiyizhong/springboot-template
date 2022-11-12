package com.zhenmei.wsc.rbac.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhenmei.wsc.rbac.exception.AuthorizeException;
import com.zhenmei.wsc.rbac.mybatis.custom.entity.UserRoleEntity;
import com.zhenmei.wsc.rbac.mybatis.custom.mapper.RoleDao;
import com.zhenmei.wsc.rbac.mybatis.generate.entity.TAdminUser;
import com.zhenmei.wsc.rbac.mybatis.generate.mapper.TAdminUserMapper;
import com.zhenmei.wsc.rbac.pojo.bo.RoleBo;
import com.zhenmei.wsc.rbac.pojo.bo.TokenBo;
import com.zhenmei.wsc.rbac.pojo.form.LoginForm;
import com.zhenmei.wsc.rbac.pojo.vo.LoginVo;
import com.zhenmei.wsc.rbac.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
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
            List<UserRoleEntity> roleList = roleDao.getRoleListByUserId(user.getId());
            List<RoleBo> roleCollect = roleList.stream().map(userRoleEntity ->
                    RoleBo.builder().roleId(userRoleEntity.getRoleId()).roleName(userRoleEntity.getRoleName()).build()
            ).collect(Collectors.toList());
            TokenBo tokenBo = new TokenBo();
            tokenBo.setUserId(user.getId());
            tokenBo.setRoleList(roleCollect);
            tokenBo.setCurrentRoleId(user.getCurrentRoleId());
            String token = JWTUtil.createToken(BeanUtil.beanToMap(tokenBo), "salt?".getBytes());
            loginVo.setToken(token);
            loginVo.setRoleList(roleCollect);
            return loginVo;
        } else {
            throw new AuthorizeException("无效的用户名或密码");
        }

    }


}
