package com.ruyuan.cloud.lock.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redisson配置类
 *
 * @author zhonghuashishan
 */
@Data
@ConfigurationProperties(prefix = "ruyuan.distributed.lock")
public class DistributedLockProperties {

	/**
	 * 分布式锁类型：redis/zk
	 */
	private String type;

	/**
	 * server地址，ip：port，有多个用半角逗号分隔
	 */
	private String address;

	/**
	 * 连接密码
	 */
	private String password;

	/**
	 * redis server的工作模式：standalone，sentinel、cluster、master-slave
	 */
	private String redisWorkMode;
	/**
	 * 选取数据库
	 */
	private int redisDatabase;

}
