package com.example.springaop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component // @Bean은 class에 붙힐 수 없다 method에서 쓸 수 있음
public class TimerAop {
    @Pointcut("execution(* com.example.springaop.controller..*.*(..))")
    private void cut(){}

    @Pointcut("@annotation(com.example.springaop.annotation.Timer)")
    private void enableTimer(){}

    @Around("cut() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();

        System.out.println("total time : "+stopWatch.getTotalTimeSeconds());
    }
}
