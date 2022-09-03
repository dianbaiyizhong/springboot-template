package com.zhenmei.wsc.security.controller;

import com.zhenmei.wsc.form.BasePageForm;
import com.zhenmei.wsc.response.ResultBuilder;
import com.zhenmei.wsc.security.service.AdminUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/security")
@CrossOrigin
public class AdminUserController {

    @Resource
    private AdminUserService adminUserService;

    @RequestMapping(value = "/admin/list", method = RequestMethod.GET)
    public Object list(@RequestBody @Valid BasePageForm form) {

        return ResultBuilder.success(adminUserService.list(form));
    }


}
