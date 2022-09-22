package com.zhenmei.wsc.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper  {
    @Select("select * from t_admin_user")
    List<Map<String, String>> getList();
}
