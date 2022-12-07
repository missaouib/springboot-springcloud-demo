package com.example.jmhmaven.log;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class RestTemplateFactory {

    private static final RestTemplateFactory INSTANCE = new RestTemplateFactory();

    private RestTemplateFactory() {

    }

    public static RestTemplateFactory getInstance() {
        return INSTANCE;
    }

    public RestTemplate create() {
        //设置RestTemplate的超时时间
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(2000);
        requestFactory.setConnectTimeout(2000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        return restTemplate;
    }
}
