package com.zhenmei.template.interfaces.assembler;

import com.zhenmei.template.infrastructure.mybatisplus.generate.entity.TUser;
import com.zhenmei.template.interfaces.dto.UserDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户领域实体和dto互转编译器
 *
 * @since
 */
public interface UserAssembler {


    static List<UserDTO> toDTOList(List<TUser> userList) {


        return userList.stream().map(user ->
             toDTO(user)
        ).collect(Collectors.toList());

    }


    static UserDTO toDTO(TUser entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        return UserDTO.builder()
                .nickName(entity.getName())
                .build();
    }
}
