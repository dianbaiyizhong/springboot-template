package com.zhenmei.demo.controller;

import com.zhenmei.wsc.rbac.mybatis.custom.mapper.PermissionDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("wx")
@ResponseBody
public class WxController {

    @Resource
    private PermissionDao permissionDao;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public Object test() {

        System.out.println(permissionDao.findAll());

        return "上传成功";
    }


}
