package com.example.txaspect.aop;

import com.example.txaspect.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AdviceAopOrderAspect {

    @Pointcut("execution(* com.example.txaspect.service.UserService.*(..))")
    public void pointcut(){}

    @Around(value = "pointcut()  && target(userService)")
    public Object around(ProceedingJoinPoint pjp, UserService userService) throws Throwable {
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
