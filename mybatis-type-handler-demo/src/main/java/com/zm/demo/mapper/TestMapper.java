package com.zm.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
public interface TestMapper {
    @Select("select * from t_admin_user")
    List<Map<String, String>> getList();
}
