package com.ruyuan.cloud.sms.template;

import com.ruyuan.cloud.sms.entity.SmsData;
import com.ruyuan.cloud.sms.entity.SmsResponse;
import com.ruyuan.cloud.sms.properties.SmsProperties;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Collection;


/**
 * 腾讯云短信发送类
 *
 * @author zhongshuashishan
 */
@RequiredArgsConstructor
public class TencentSmsTemplate implements SmsTemplate {

    private final SmsProperties smsProperties;

    @Override
    public SmsResponse sendMessage(SmsData smsData, Collection<String> phones) {
        try {
            Credential cred = new Credential(smsProperties.getAccessKey(), smsProperties.getSecretKey());
            SmsClient client = new SmsClient(cred, "ap-guangzhou");
            SendSmsRequest req = new SendSmsRequest();
            req.setSmsSdkAppId(smsProperties.getSdkAppId());
            req.setSignName(smsProperties.getSignName());
            req.setTemplateId(smsProperties.getTemplateId());
            req.setPhoneNumberSet((String[]) phones.toArray());
            Collection<String> values = smsData.getParams().values();
            req.setTemplateParamSet((String[]) values.toArray());
            SendSmsResponse res = client.SendSms(req);
            return new SmsResponse(true, "200", res.toString());
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
            return new SmsResponse(Boolean.FALSE, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    e.getMessage());
        }
    }

}
