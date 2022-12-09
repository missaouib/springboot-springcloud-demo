package com.ruyuan.cloud.sms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * 通知内容
 *
 * @author zhongshuashishan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SmsData implements Serializable {

    /**
     * 参数列表
     */
    private Map<String, String> params;
}
