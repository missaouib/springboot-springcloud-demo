package com.ruyuan.cloud.sms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 短信返回集合
 *
 * @author zhongshuashishan
 */
@Data
@AllArgsConstructor
public class SmsResponse implements Serializable {

	private static final long serialVersionUID = -8243013406812841125L;

	/**
	 * 是否成功
	 */
	private boolean success;

	/**
	 * 状态码
	 */
	private String code;

	/**
	 * 返回消息
	 */
	private String msg;
}
