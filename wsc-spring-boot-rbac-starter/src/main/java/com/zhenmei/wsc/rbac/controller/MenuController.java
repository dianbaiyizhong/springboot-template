package com.zhenmei.wsc.rbac.controller;

import com.zhenmei.wsc.form.BasePageForm;
import com.zhenmei.wsc.form.IdAndPageForm;
import com.zhenmei.wsc.form.RoleAuthorityForm;
import com.zhenmei.wsc.rbac.service.AdminMenuService;
import com.zhenmei.wsc.rbac.service.AdminRoleService;
import com.zhenmei.wsc.response.ResultBuilder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
