package com.zhenmei.service;

import com.zhenmei.pojo.param.DeviceInfoAddParam;
import com.zhenmei.pojo.param.DeviceInfoGetParam;

public interface DeviceService {


    public void listDevice(DeviceInfoGetParam param);

    public void addDevice(DeviceInfoAddParam param);

    public void getUnSuccess();

    public void testTransactional();

}
