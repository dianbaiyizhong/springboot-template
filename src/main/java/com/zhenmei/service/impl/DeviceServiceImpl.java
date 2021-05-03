package com.zhenmei.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhenmei.constant.RestCode;
import com.zhenmei.exception.BusinessException;
import com.zhenmei.exception.SuccessException;
import com.zhenmei.mybatis.custom.dao.CustomDeviceMapper;
import com.zhenmei.mybatis.generate.entity.TDeviceInfoEntity;
import com.zhenmei.mybatis.generate.mapper.TDeviceInfoMapper;
import com.zhenmei.pojo.param.DeviceInfoAddParam;
import com.zhenmei.pojo.param.DeviceInfoGetParam;
import com.zhenmei.pojo.vo.DeviceInfoVo;
import com.zhenmei.response.ServiceMultiResultVO;
import com.zhenmei.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {


    @Autowired
    private TDeviceInfoMapper deviceInfoMapper;

    @Autowired
    private CustomDeviceMapper customDeviceMapper;

    @Override
    public void listDevice(DeviceInfoGetParam param) {
        Page<TDeviceInfoEntity> pager = new Page<>(param.getPage(), param.getRows());

        deviceInfoMapper.selectPage(pager, null);


        // 起名字为dbList，容易辨认该list就是数据库的实体对象类型
        List<TDeviceInfoEntity> dbList = Optional.ofNullable(pager.getRecords()).orElse(new ArrayList<>(0));


        // 数据类型转换
        List<DeviceInfoVo> retList = dbList.stream().map(tDeviceInfoDO -> DeviceInfoVo
                .builder()
                .deviceId(tDeviceInfoDO.getDeviceId())
                .deviceName(tDeviceInfoDO.getDeviceName())
                .createTime(tDeviceInfoDO.getGmtCreate())
                .build()
        ).collect(Collectors.toList());

        throw new SuccessException(new ServiceMultiResultVO(pager.getTotal(), retList));

    }

    @Override
    public void addDevice(DeviceInfoAddParam param) {


        TDeviceInfoEntity record = new TDeviceInfoEntity();
        record.setDeviceId(IdUtil.createSnowflake(1, 1).nextIdStr());
        record.setDeviceIp(param.getDeviceIp());
        record.setDeviceName(param.getDeviceName());
        System.out.println(LocalDateTime.now());
        record.setGmtCreate(LocalDateTime.now());
        deviceInfoMapper.insert(record);
    }

    @Override
    public void getUnSuccess() {

        customDeviceMapper.updateMySelf();
        throw BusinessException.builder()
                .message("unSuccess")
                .clientTip("你已经抢到该站火车票")
                .restCode(RestCode.CODE_409001)
                .build();
    }

    @Override
    @Transactional
    public void testTransactional() {

        TDeviceInfoEntity insertRecord = new TDeviceInfoEntity();
        insertRecord.setDeviceId(UUID.randomUUID().toString());
        insertRecord.setDeviceIp("192.168.10.3");
        insertRecord.setDeviceName("test");
        insertRecord.setGmtCreate(LocalDateTime.now());
        deviceInfoMapper.insert(insertRecord);

        // 使用全局异常捕获后，@Transactional依然是生效的
        throw BusinessException.builder()
                .message("unSuccess")
                .clientTip("你已经抢到该站火车票")
                .restCode(RestCode.CODE_409001)
                .build();

    }


}
