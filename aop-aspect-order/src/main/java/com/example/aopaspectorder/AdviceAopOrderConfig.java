package com.example.aopaspectorder;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
public class AdviceAopOrderConfig {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AdviceAopOrderConfig.class, AdviceAopOrderAspect2.class,AdviceAopOrderAspect.class, AdviceAopService.class);
        context.refresh();

        AdviceAopService adviceAopService = context.getBean(AdviceAopService.class);
        adviceAopService.print(1,1);

        context.close();
    }
}
