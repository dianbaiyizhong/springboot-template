package com.zhenmei.demo.controller;

import com.zhenmei.wsc.rbac.annotion.RbacCheck;
import com.zhenmei.wsc.rbac.mybatis.custom.mapper.PermissionDao;
import com.zhenmei.wsc.response.ResultBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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



    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Object query() {
        return ResultBuilder.success();
    }


    /**
     * admin查询接口，管理员才有权限
     * @return
     */
    @RequestMapping(value = "/admin/query", method = RequestMethod.GET)
    public Object queryAdmin() {
        return ResultBuilder.success();
    }


    /**
     * audit查询接口，审计员才有权限
     * @return
     */
    @RequestMapping(value = "/audit/query", method = RequestMethod.GET)
    public Object queryAudit() {
        return ResultBuilder.success();
    }

}
