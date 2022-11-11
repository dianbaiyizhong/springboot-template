package com.zhenmei.wsc.rbac.service;

import com.zhenmei.wsc.rbac.pojo.form.LoginForm;
import com.zhenmei.wsc.rbac.pojo.vo.LoginVo;

public interface AuthService {
    public LoginVo login(LoginForm loginForm);
}
