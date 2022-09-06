package com.zhenmei.wsc.security.convertor;

import com.zhenmei.wsc.security.mybatis.custom.entity.UserRoleEntity;
import com.zhenmei.wsc.security.mybatis.generate.entity.TAdminUser;
import com.zhenmei.wsc.security.mybatis.generate.entity.TRole;
import com.zhenmei.wsc.security.pojo.dto.AdminUserDTO;
import com.zhenmei.wsc.security.pojo.dto.RoleDTO;
import com.zhenmei.wsc.security.pojo.dto.UserRoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class MapStructConvertor extends BaseConvertor {
    public static final MapStructConvertor INSTANCE = Mappers.getMapper(MapStructConvertor.class);

//    @Mapping(target = "publishDate",expression = "java(parseDate(shopDTO.getPublishDate()))")

    @Mappings({
            @Mapping(source = "gmtCreate", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "userName", target = "userName"),
            @Mapping(source = "id", target = "id")


    })
    public abstract AdminUserDTO userDo2UserDto(TAdminUser entity);

    public abstract List<AdminUserDTO> userDo2UserDto(List<TAdminUser> list);




    @Mappings({
            @Mapping(source = "gmtCreate", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "roleName", target = "roleName"),
            @Mapping(source = "id", target = "id")
    })
    public abstract RoleDTO roleDo2RoleDto(TRole entity);

    public abstract List<RoleDTO> roleDo2RoleDto(List<TRole> list);



    @Mappings({
            @Mapping(source = "roleName", target = "roleName"),
            @Mapping(source = "roleId", target = "roleId")
    })
    public abstract UserRoleDTO userRoleDo2Dto(UserRoleEntity entity);

    public abstract List<UserRoleDTO> userRoleDo2Dto(List<UserRoleEntity> list);




}
