package com.example.commonutils;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;

import java.lang.reflect.Field;

/**
 * Aop代理工具类
 * @author zhonghuashishan
 * @version 1.0
 */
public class AopUtil {

    /**
     * 获取 目标对象
     * @param proxy 代理对象
     */
    public static Object getTarget(Object proxy) throws Exception {

        if(!org.springframework.aop.support.AopUtils.isAopProxy(proxy)) {
            // 不是代理对象
            return proxy;
        }

        if(org.springframework.aop.support.AopUtils.isJdkDynamicProxy(proxy)) {
            return getJdkDynamicProxyTargetObject(proxy);
        } else { // cglib
            return getCglibProxyTargetObject(proxy);
        }
    }

    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);

        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        Object target = ((AdvisedSupport)advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();

        return target;
    }


    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);

        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        Object target = ((AdvisedSupport)advised.get(aopProxy)).getTargetSource().getTarget();

        return target;
    }
}
