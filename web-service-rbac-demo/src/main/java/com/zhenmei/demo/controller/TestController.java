package com.zhenmei.demo.controller;

import com.zhenmei.wsc.rbac.annotion.RbacCheck;
import com.zhenmei.wsc.rbac.mybatis.custom.mapper.PermissionDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("test")
@ResponseBody
public class TestController {

    @Resource
    private PermissionDao permissionDao;

    @RequestMapping(value = "/go", method = RequestMethod.GET)
    @ResponseBody
    @RbacCheck("news_list")
    public Object go() {

        return "上传成功";
    }


}
