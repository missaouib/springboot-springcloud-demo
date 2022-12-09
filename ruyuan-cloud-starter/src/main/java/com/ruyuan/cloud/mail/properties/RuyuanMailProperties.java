package com.ruyuan.cloud.mail.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.HashMap;
import java.util.Map;

/**
 * mail属性
 *
 * @author zhonghuashishan
 **/
@Data
@ConfigurationProperties(prefix = "ruyuan.mail")
public class RuyuanMailProperties {

    /**
     * 是否开启mail功能
     */
    private boolean enable;

    /**
     * SMTP server host
     */
    private String host;

    /**
     * SMTP server的端口
     */
    private Integer port;

    /**
     * SMTP server的用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮件协议
     */
    private String protocol = "smtp";

    /**
     * 额外要增加的属性
     */
    private Map<String, String> properties = new HashMap<>();

}
