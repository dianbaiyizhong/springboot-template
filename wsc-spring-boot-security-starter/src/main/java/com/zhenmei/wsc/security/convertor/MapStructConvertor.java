package com.zhenmei.wsc.security.convertor;

import com.zhenmei.wsc.security.mybatis.generate.entity.TAdminUser;
import com.zhenmei.wsc.security.pojo.dto.AdminUserDTO;
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
//            @Mapping(source = "gmtCreate", target = "createTime")
            @Mapping(source = "userName", target = "userName")

    })
    public abstract AdminUserDTO shopDto2shopVo(TAdminUser entity);

    public abstract List<AdminUserDTO> shopDto2shopVos(List<TAdminUser> list);

}
