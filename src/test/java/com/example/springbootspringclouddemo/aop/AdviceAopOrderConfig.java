package com.example.springbootspringclouddemo.aop;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableTransactionManagement
public class AdviceAopOrderConfig {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AdviceAopOrderConfig.class,AdviceAopOrderAspect.class,AdviceAopOrderAspect.class,AdviceAopService.class);
        context.refresh();

        AdviceAopService adviceAopService = context.getBean(AdviceAopService.class);
        adviceAopService.print(1,1);

        context.close();
    }
}
