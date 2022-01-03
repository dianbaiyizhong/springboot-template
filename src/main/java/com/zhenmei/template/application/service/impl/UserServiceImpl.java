package com.zhenmei.template.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhenmei.template.application.service.UserService;
import com.zhenmei.template.infrastructure.mybatisplus.generate.entity.TUser;
import com.zhenmei.template.infrastructure.mybatisplus.generate.mapper.TUserMapper;
import com.zhenmei.template.interfaces.assembler.UserAssembler;
import com.zhenmei.template.interfaces.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TUserMapper userMapper;

    @Override
    public UserDTO getUser(String id) {


        TUser user = userMapper.selectOne(new QueryWrapper<TUser>()
                .lambda()
                .eq(TUser::getId, id));

        if (user == null) {

            return null;
        } else {
            return UserAssembler.toDTO(user);
        }
    }
}
