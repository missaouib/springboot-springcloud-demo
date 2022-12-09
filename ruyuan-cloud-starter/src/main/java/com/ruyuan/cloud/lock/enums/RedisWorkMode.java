package com.ruyuan.cloud.lock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * redis部署方式的枚举
 *
 * @author zhonghuashishan
 */
@Getter
@AllArgsConstructor
public enum RedisWorkMode {
	/**
	 * 单节点部署方式
	 */
	WORK_MODE_STANDALONE("redis-single", "redis以单节点部署方式"),
	/**
	 * 哨兵部署方式
	 */
	WORK_MODE_SENTINEL("redis-sentinel", "哨兵部署方式"),
	/**
	 * 集群部署方式
	 */
	WORK_MODE_CLUSTER("redis-cluster", "集群方式"),
	/**
	 * 主从部署方式
	 */
	WORK_MODE_MASTER_SLAVE("master-slave", "主从部署方式");

	private final String workMode;

	private final String desc;

}
