package com.example.springbootspringclouddemo.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRegistration;

@Configuration
public class HystrixConfig {

    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);//系统启动时加载
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("hystrixMetricsStreamServlet");
        return registrationBean;
    }
}
