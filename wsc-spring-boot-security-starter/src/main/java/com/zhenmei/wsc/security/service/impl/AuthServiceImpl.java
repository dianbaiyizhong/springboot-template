package com.zhenmei.wsc.security.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson.JSONObject;
import com.zhenmei.wsc.exception.AuthorizeException;
import com.zhenmei.wsc.security.core.UserAuthInfo;
import com.zhenmei.wsc.security.form.LoginForm;
import com.zhenmei.wsc.security.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public JSONObject login(LoginForm loginForm, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
        JSONObject jsonObject = new JSONObject();
        try {
            Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserAuthInfo userAuthInfo = (UserAuthInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String token = JWTUtil.createToken(BeanUtil.beanToMap(userAuthInfo), "salt?".getBytes());
            // 将token放置请求头返回
            jsonObject.put("token", token);
            jsonObject.put("roles", userAuthInfo.getRoles());
        } catch (Exception e) {
            throw new AuthorizeException(e.getMessage());
        }
        return jsonObject;

    }


}
