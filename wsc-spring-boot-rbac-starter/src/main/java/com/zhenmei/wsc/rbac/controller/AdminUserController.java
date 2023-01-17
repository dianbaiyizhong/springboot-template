package com.zhenmei.wsc.rbac.controller;

import com.zhenmei.wsc.form.BasePageForm;
import com.zhenmei.wsc.form.BatchDeleteForm;
import com.zhenmei.wsc.rbac.pojo.form.AdminUserSaveForm;
import com.zhenmei.wsc.rbac.service.AdminUserService;
import com.zhenmei.wsc.response.ResultBuilder;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wsc/rbac")
@CrossOrigin
public class AdminUserController {

    @Resource
    private AdminUserService adminUserService;

    @RequestMapping(value = "/admin/list", method = RequestMethod.GET)
    public Object list(@Valid BasePageForm form) {

        return ResultBuilder.success(adminUserService.list(form));
    }

    @RequestMapping(value = "/admin/save", method = RequestMethod.POST)
    public Object list(@RequestBody @Valid AdminUserSaveForm form) {
        adminUserService.saveAdminUser(form);

        return ResultBuilder.buildSuccess("添加用户成功");
    }


    @RequestMapping(value = "/admin/del", method = RequestMethod.POST)
    public Object deleteUser(@RequestBody @Valid BatchDeleteForm form) {
        adminUserService.deleteUser(form);
        return ResultBuilder.buildSuccess("删除用户成功");
    }

}
