package com.zhenmei.wsc.security.service;

import com.alibaba.fastjson.JSONObject;
import com.zhenmei.wsc.security.pojo.form.LoginForm;

import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    public JSONObject login(LoginForm loginForm, HttpServletResponse response);

}
