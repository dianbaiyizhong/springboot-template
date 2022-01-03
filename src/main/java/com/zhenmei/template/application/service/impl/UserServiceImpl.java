package com.zhenmei.template.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhenmei.template.application.service.UserService;
import com.zhenmei.template.infrastructure.constant.RestCode;
import com.zhenmei.template.infrastructure.exception.BusinessException;
import com.zhenmei.template.infrastructure.mybatisplus.generate.entity.TUser;
import com.zhenmei.template.infrastructure.mybatisplus.generate.mapper.TUserMapper;
import com.zhenmei.template.interfaces.assembler.UserAssembler;
import com.zhenmei.template.interfaces.dto.UserDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

            throw BusinessException
                    .builder()
                    .restCode(RestCode.NOT_FOUND_RECORD)
                    .clientTip("找不到用户...")
                    .build();

        } else {
            return UserAssembler.toDTO(user);
        }
    }

    @Override
    public List<UserDTO> getAllUser() {


        List<TUser> userList = userMapper.selectList(null);

        if(CollectionUtils.isEmpty(userList)){
            throw BusinessException
                    .builder()
                    .restCode(RestCode.NOT_FOUND_RECORD)
                    .clientTip("找不到记录...")
                    .build();
        } else {
            return UserAssembler.toDTOList(userList);
        }



    }
}
