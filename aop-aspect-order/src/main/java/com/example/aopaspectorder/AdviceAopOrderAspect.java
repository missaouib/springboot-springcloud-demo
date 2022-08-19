package com.example.aopaspectorder;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

@Aspect
//@Order(1)
public class AdviceAopOrderAspect {

    @Pointcut("execution(* com.example.aopaspectorder.AdviceAopService.*(..))")
    public void pointcut(){}

    @Around(value = "pointcut()  && target(aopService)")
    public Object around(ProceedingJoinPoint pjp,AdviceAopService aopService) throws Throwable {
        System.out.println("Aspect @Around begin===============================");
        Object result = pjp.proceed();
        System.out.println("Aspect @Around end===============================");
        return result;
    }

    @Before("pointcut()")
    public void before() {
        System.out.println("Aspect @Before===============================");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("Aspect @After===============================");
    }

    @AfterReturning("pointcut()")
    public void afterReturning() {
        System.out.println("Aspect @AfterReturning===============================");
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        System.out.println("Aspect @AfterThrowing===============================");
    }
}
