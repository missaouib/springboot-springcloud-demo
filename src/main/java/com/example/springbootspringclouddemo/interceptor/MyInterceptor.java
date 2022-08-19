package com.example.springbootspringclouddemo.interceptor;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MyInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return "haha";
    }

    @Override
    public Object plugin(Object target) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
