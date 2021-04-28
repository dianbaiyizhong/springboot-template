package com.zhenmei.controller;


import com.zhenmei.pojo.param.DeviceInfoGetParam;
import com.zhenmei.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("deviceInfo")
@ResponseBody
public class DeviceInfoController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping(value = "list")
    public void list(@Valid DeviceInfoGetParam param) {
        deviceService.listDevice(param);
    }


    @GetMapping(value = "unsuccess")
    public void unsuccess() {
        deviceService.getUnSuccess();
    }

    @GetMapping(value = "test/transactional")
    public void testTransactional() {
        deviceService.testTransactional();
    }
}

