package com.ruyuan.cloud.lock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 常量枚举
 *
 * @author zhonghuashishan
 */
@Getter
@AllArgsConstructor
public enum RedissionConstant {

	/**
	 * Redis地址连接前缀
	 */
	REDISSION_CONNECTION_PREFIX("redis://", "Redission地址前缀,配置redission客户端时使用");

	private final String value;

	private final String desc;
}
