package com.example.aopaspectorder;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

@Aspect
public class AdviceAopOrderAspect2 {

    @Pointcut("execution(* com.example.aopaspectorder.AdviceAopService.*(..))")
    public void pointcut(){}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Aspect2 @Around begin===============================");
        Object result = pjp.proceed();
        System.out.println("Aspect2 @Around end===============================");
        return result;
    }

    @Before("pointcut()")
    public void before() {
        System.out.println("Aspect2 @Before===============================");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("Aspect2 @After===============================");
    }

    @AfterReturning("pointcut()")
    public void afterReturning() {
        System.out.println("Aspect2 @AfterReturning===============================");
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        System.out.println("Aspect2 @AfterThrowing===============================");
    }
}
