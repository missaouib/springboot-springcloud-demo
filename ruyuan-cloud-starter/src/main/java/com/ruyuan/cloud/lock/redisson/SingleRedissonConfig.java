package com.ruyuan.cloud.lock.redisson;

import com.ruyuan.cloud.lock.enums.RedissionConstant;
import com.ruyuan.cloud.lock.properties.DistributedLockProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.config.Config;

/**
 * 单机方式Redisson配置
 *
 * @author zhonghuashishan
 */
@Slf4j
public class SingleRedissonConfig {

	public static Config createRedissonConfig(DistributedLockProperties redissonProperties) {
		Config config = new Config();
		try {
			// redis地址和连接密码，db
			String address = redissonProperties.getAddress();
			String password = redissonProperties.getPassword();
			int database = redissonProperties.getRedisDatabase();
			// redis://127.0.0.1:3306/
			String redisAddr = RedissionConstant.REDISSION_CONNECTION_PREFIX.getValue() + address;
			config.useSingleServer().setAddress(redisAddr);
			config.useSingleServer().setDatabase(database);

			if (StringUtils.isNotBlank(password)) {
				config.useSingleServer().setPassword(password);
			}
			log.info("初始化方式Config, mode=standalone, redisAddress={},", address);
		} catch (Exception e) {
			log.error("standalone Redisson init error", e);
			e.printStackTrace();
		}
		return config;
	}
}
