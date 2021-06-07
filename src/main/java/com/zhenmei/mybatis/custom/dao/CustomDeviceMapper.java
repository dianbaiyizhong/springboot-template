package com.zhenmei.mybatis.custom.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhenmei.mybatis.generate.entity.TDeviceInfoEntity;

public interface CustomDeviceMapper extends BaseMapper<TDeviceInfoEntity> {
    public void updateMySelf();
}
