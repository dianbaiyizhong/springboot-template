package com.zhenmei.wsc.security.demo.controller;


import com.zhenmei.wsc.response.ResultBuilder;
import com.zhenmei.wsc.security.pojo.form.LoginForm;
import com.zhenmei.wsc.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("auth")
@CrossOrigin
@ResponseBody
public class AuthController {


    @Autowired
    private AuthService authService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody @Valid LoginForm loginForm, HttpServletResponse response) {


        return ResultBuilder.success(authService.login(loginForm, response));

    }


}
