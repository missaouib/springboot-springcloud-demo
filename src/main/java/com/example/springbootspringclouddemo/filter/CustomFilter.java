package com.example.springbootspringclouddemo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

public class CustomFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //获取上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        //获取request
        HttpServletRequest request = requestContext.getRequest();
        //获取请求的url路径
        String requestURI = request.getRequestURI();
        //判断是否放行，如果为false表示放行，true表示拦截
        if (true) {
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if (!false) {
            //拦截
            requestContext.setSendZuulResponse(false);
            //设置状态码
            requestContext.setResponseStatusCode(403);
        }
        //放行
        return null;
    }
}
