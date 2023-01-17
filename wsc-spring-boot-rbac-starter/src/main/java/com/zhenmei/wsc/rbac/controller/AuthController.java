package com.zhenmei.wsc.rbac.controller;


import com.zhenmei.wsc.rbac.pojo.form.LoginForm;
import com.zhenmei.wsc.rbac.service.AuthService;
import com.zhenmei.wsc.response.ResultBuilder;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("auth")
@CrossOrigin
@ResponseBody
public class AuthController {


    @Resource
    private AuthService authService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody @Valid LoginForm loginForm) {

        return ResultBuilder.success(authService.login(loginForm));

    }


}
