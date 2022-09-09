package com.zhenmei.wsc.security.controller;

import com.zhenmei.wsc.form.BasePageForm;
import com.zhenmei.wsc.form.IdAndPageForm;
import com.zhenmei.wsc.form.RoleAuthorityForm;
import com.zhenmei.wsc.response.ResultBuilder;
import com.zhenmei.wsc.security.pojo.form.AdminUserSaveForm;
import com.zhenmei.wsc.security.service.AdminRoleService;
import com.zhenmei.wsc.security.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/security")
@CrossOrigin
public class RoleController {
    @Resource
    private HttpServletRequest request;
    @Resource
    private AdminRoleService adminRoleService;

    @RequestMapping(value = "/role/list", method = RequestMethod.GET)
    public Object list(@Valid BasePageForm form, @RequestAttribute(value = "requestUserId", required = true) String requestUserId) {
        form.setRequestUserId(requestUserId);
        return ResultBuilder.success(adminRoleService.list(form));
    }


    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public Object getRolesByUserId(@RequestBody @Valid IdAndPageForm form) {
        return ResultBuilder.success(adminRoleService.listByUserId(form));
    }


    @RequestMapping(value = "/role/authority", method = RequestMethod.POST)
    public Object authority(@RequestBody @Valid RoleAuthorityForm form, @RequestAttribute(value = "requestUserId", required = true) String requestUserId) {
        form.setRequestUserId(requestUserId);
        adminRoleService.authorityRole(form);
        return ResultBuilder.buildSuccess("授权操作成功");
    }


}
