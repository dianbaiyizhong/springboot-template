package com.zhenmei.wsc.rbac.aop;

import com.zhenmei.wsc.rbac.annotion.RbacCheck;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class RbacAspect {

    @Resource
    private HttpServletRequest request;

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
        RbacCheck rbacCheck = method.getAnnotation(RbacCheck.class);
        System.out.println(rbacCheck.value());
        Object res = proceedingJoinPoint.proceed();

        



        return res;
    }
}
