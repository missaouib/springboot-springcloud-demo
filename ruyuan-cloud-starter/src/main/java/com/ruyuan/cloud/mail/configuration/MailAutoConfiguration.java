package com.ruyuan.cloud.mail.configuration;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

import com.ruyuan.cloud.mail.properties.RuyuanMailProperties;
import com.ruyuan.cloud.mail.template.JavaMailTemplate;
import com.ruyuan.cloud.mail.template.MailTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 邮件配置
 *
 * @author zhonghuashishan
 */
@Configuration
@ConditionalOnProperty(value = "ruyuan.mail.enable", havingValue = "true")
@EnableConfigurationProperties(RuyuanMailProperties.class)
public class MailAutoConfiguration {

    @Bean
    @ConditionalOnClass(MailTemplate.class)
    public MailTemplate mailTemplate(RuyuanMailProperties properties) {
        // 发送邮件是基于spring框架提供的发送邮件API，封装和发送邮件
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        MailProperties mailProperties = mailProperties(properties);
        mailSender.setHost(mailProperties.getHost());
        mailSender.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        mailSender.setUsername(mailProperties.getUsername());
        mailSender.setPassword(mailProperties.getPassword());
        mailSender.setPort(mailProperties.getPort());
        mailSender.setProtocol(mailProperties.getProtocol());
        mailSender.setJavaMailProperties(asProperties(properties.getProperties()));

        return new JavaMailTemplate(mailSender, mailProperties);
    }

    @Bean
    @ConditionalOnClass(MailTemplate.class)
    public MailProperties mailProperties(RuyuanMailProperties properties) {
        MailProperties mailProperties = new MailProperties();
        mailProperties.setHost(properties.getHost()); // 邮件服务器的地址
        mailProperties.setPort(properties.getPort()); // 邮件服务器的端口号
        mailProperties.setUsername(properties.getUsername()); // 用户名
        mailProperties.setPassword(properties.getPassword()); // 密码
        mailProperties.setProtocol(properties.getProtocol()); // 通信协议
        mailProperties.setDefaultEncoding(StandardCharsets.UTF_8); // 默认encoding编码的格式
        return mailProperties;
    }

    private Properties asProperties(Map<String, String> source) {
        Properties properties = new Properties();
        properties.putAll(source);
        return properties;
    }

}