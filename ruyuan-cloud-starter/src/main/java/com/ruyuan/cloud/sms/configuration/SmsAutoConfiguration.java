package com.ruyuan.cloud.sms.configuration;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import com.ruyuan.cloud.sms.properties.SmsProperties;
import com.ruyuan.cloud.sms.template.AliSmsTemplate;
import com.ruyuan.cloud.sms.template.SmsTemplate;
import com.ruyuan.cloud.sms.template.TencentSmsTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 短信自动装配类
 *
 * @author zhonghuashishan
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties(SmsProperties.class)
public class SmsAutoConfiguration {

    @Bean
    @ConditionalOnClass(Client.class)
    @ConditionalOnProperty(value = "ruyuan.sms.type", havingValue = "aliyun")
    public SmsTemplate aliSmsTemplate(SmsProperties smsProperties) throws Exception {
        Config config = new Config()
                .setAccessKeyId(smsProperties.getAccessKey())
                .setAccessKeySecret(smsProperties.getSecretKey());
        config.endpoint = "dysmsapi.aliyuncs.com";
        Client client = new Client(config);
        return new AliSmsTemplate(smsProperties, client);
    }

    @Bean
    @ConditionalOnProperty(value = "ruyuan.sms.type", havingValue = "tencent")
    public SmsTemplate tencentSmsTemplate(SmsProperties smsProperties) {
        return new TencentSmsTemplate(smsProperties);
    }

}
