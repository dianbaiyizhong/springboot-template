package com.zhenmei.wsc.rbac.controller;

import com.zhenmei.wsc.rbac.service.AdminMenuService;
import com.zhenmei.wsc.response.ResultBuilder;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wsc/rbac")
@CrossOrigin
public class MenuController {
    @Resource
    private HttpServletRequest request;
    @Resource
    private AdminMenuService adminMenuService;

    @RequestMapping(value = "/menu/list", method = RequestMethod.GET)
    public Object list() {
        List<Map<String, Object>> list = adminMenuService.list();
        return ResultBuilder.success(list);
    }

}
