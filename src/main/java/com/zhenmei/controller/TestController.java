package com.zhenmei.controller;


import com.zhenmei.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test")
@ResponseBody
public class TestController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping(value = "show")
    public String show() {
        return "success";
    }
}

