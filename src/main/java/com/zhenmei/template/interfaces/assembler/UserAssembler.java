package com.zhenmei.template.interfaces.assembler;

import com.zhenmei.template.infrastructure.mybatisplus.generate.entity.TUser;
import com.zhenmei.template.interfaces.dto.UserDTO;

import java.util.Objects;

/**
 * 用户领域实体和dto互转编译器
 *
 * @author xiaofeifei
 * @date 2020-02-03
 * @since
 */
public interface UserAssembler {


    static UserDTO toDTO(TUser entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        return UserDTO.builder()
                .nickName(entity.getName())
                .build();
    }
}
