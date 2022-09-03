package com.zhenmei.wsc.security.demo.controller;

import com.zhenmei.wsc.response.ResultBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("test")
@CrossOrigin
@ResponseBody
public class TestController {


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
