package com.ruyuan.cloud.sms.template;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.ruyuan.cloud.common.utils.JsonUtil;
import com.ruyuan.cloud.common.utils.StringUtil;
import com.ruyuan.cloud.sms.entity.SmsData;
import com.ruyuan.cloud.sms.entity.SmsResponse;
import com.ruyuan.cloud.sms.properties.SmsProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

/**
 * 阿里云短信发送类
 *
 * @author zhongshuashishan
 */
@Slf4j
@AllArgsConstructor
public class AliSmsTemplate implements SmsTemplate {

    private static final String OK = "ok";

    private final SmsProperties smsProperties;
    private final Client client;

    @Override
    public SmsResponse sendMessage(SmsData smsData, Collection<String> phones) {
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(StringUtil.join(phones))
                .setSignName(smsProperties.getSignName())
                .setTemplateCode(smsProperties.getTemplateId())
                .setTemplateParam(JsonUtil.toJson(smsData.getParams()));
        try {
            SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
            return new SmsResponse(sendSmsResponse.getBody().getCode().equalsIgnoreCase(OK),
                    sendSmsResponse.getBody().getCode(), sendSmsResponse.getBody().getMessage());
        } catch (Exception e) {
            log.error("sendMessage fail: ", e);
            return new SmsResponse(Boolean.FALSE, "FAIL", e.getMessage());
        }
    }

}
