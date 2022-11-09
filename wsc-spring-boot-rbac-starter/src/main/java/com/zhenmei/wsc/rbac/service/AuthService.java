package com.zhenmei.wsc.rbac.service;

import com.alibaba.fastjson.JSONObject;
import com.zhenmei.wsc.rbac.pojo.form.LoginForm;
import com.zhenmei.wsc.rbac.pojo.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    public LoginVo login(LoginForm loginForm);
}
