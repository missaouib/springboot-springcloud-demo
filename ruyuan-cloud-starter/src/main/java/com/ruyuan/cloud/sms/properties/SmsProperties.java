package com.ruyuan.cloud.sms.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 短信配置
 *
 * @author zhonghuashishan
 * @version 1.0
 */
@Data
@ConfigurationProperties(prefix = "ruyuan.sms")
public class SmsProperties {

    /**
     * 短信服务类型
     */
    private String type;

    /**
     * 短信模板ID
     */
    private String templateId;

    /**
     * accessKey
     */
    private String accessKey;

    /**
     * secretKey
     */
    private String secretKey;

    /**
     * 短信签名
     */
    private String signName;

    /**
     * 腾讯云特定参数: SDKAPPID
     */
    private String sdkAppId;
}
