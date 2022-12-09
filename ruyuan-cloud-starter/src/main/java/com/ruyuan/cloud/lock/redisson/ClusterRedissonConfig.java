package com.ruyuan.cloud.lock.redisson;

import com.ruyuan.cloud.lock.enums.RedissionConstant;
import com.ruyuan.cloud.lock.properties.DistributedLockProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.config.Config;

/**
 * 集群方式Redisson配置
 *
 * @author zhonghuashishan
 */
@Slf4j
public class ClusterRedissonConfig {

	public static Config createRedissonConfig(DistributedLockProperties redissonProperties) {
		Config config = new Config();
		try {
			String address = redissonProperties.getAddress();
			String password = redissonProperties.getPassword();
			String[] addrTokens = address.split(","); // 多个节点，master，slave，节点
			// 设置cluster节点的服务IP和端口
			for (String addrToken : addrTokens) {
				config.useClusterServers()
						.addNodeAddress(RedissionConstant.REDISSION_CONNECTION_PREFIX.getValue() + addrToken);
				if (StringUtils.isNotBlank(password)) {
					config.useClusterServers().setPassword(password);
				}
			}
			log.info("初始化[cluster]方式Config,redisAddress:" + address);
		} catch (Exception e) {
			log.error("cluster Redisson init error", e);
			e.printStackTrace();
		}
		return config;
	}
}
