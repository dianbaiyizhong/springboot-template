package com.zhenmei.wsc.rbac.aop;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson2.JSON;
import com.zhenmei.wsc.rbac.annotion.RbacCheck;
import com.zhenmei.wsc.rbac.config.RbacVerification;
import com.zhenmei.wsc.rbac.exception.AuthorizeException;
import com.zhenmei.wsc.rbac.pojo.bo.TokenBo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class RbacAspect {

    @Resource
    private HttpServletRequest request;

    @Resource
    private RbacVerification rbacVerification;

    /**
     * 目标方法
     */
    @Pointcut("@annotation(com.zhenmei.wsc.rbac.annotion.RbacCheck)")
    public void authPointCut() {

    }


    @Before("authPointCut()")
    public void doBefore() {
    }

    /**
     * 目标方法调用之后执行
     */
    @After("authPointCut()")
    public void doAfter() {
    }


    @Around("authPointCut()")
    public Object authCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {


        String token = request.getHeader("Authorization");


        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        RbacCheck rbacCheck = AnnotationUtil.getAnnotation(method, RbacCheck.class);


        JWT jwt = JWTUtil.parseToken(token);
        TokenBo principal = JSON.parseObject(jwt.getPayloads().toString(), TokenBo.class);

        boolean valid = rbacVerification.valid(principal.getCurrentRoleId(), rbacCheck.value());

        if (!valid) {
            throw new AuthorizeException("无权限访问");
        }
        Object res = proceedingJoinPoint.proceed();
        return res;
    }
}
