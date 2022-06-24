package com.example.springaop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect // aop로 동작하기 위해 적음
@Component // 스프링에서 관리되어야 해서 적음
public class ParameterAop {
    @Pointcut("execution(* com.example.springaop.controller..*.*(..))")
    private void cut(){}

    @Before("cut()")
    public void before(){

    }
    public void afterReturn(){

    }
}
