package com.example.springbootspringclouddemo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AdviceAopOrderAspect {

    @Pointcut("execution(* com.example.springbootspringclouddemo.aop.AdviceAopService.*(..))")
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
