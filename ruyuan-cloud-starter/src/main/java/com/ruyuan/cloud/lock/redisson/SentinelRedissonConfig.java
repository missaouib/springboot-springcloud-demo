package com.ruyuan.cloud.lock.redisson;

import com.ruyuan.cloud.lock.enums.RedissionConstant;
import com.ruyuan.cloud.lock.properties.DistributedLockProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.config.Config;

/**
 * 哨兵方式Redis连接配置
 *
 * @author zhonghuashishan
 */
@Slf4j
public class SentinelRedissonConfig {

    public static Config createRedissonConfig(DistributedLockProperties redissonProperties) {
        Config config = new Config();
        try {
            String address = redissonProperties.getAddress();
            String password = redissonProperties.getPassword();
            int database = redissonProperties.getRedisDatabase();
            String[] addrTokens = address.split(",");
            String sentinelAliasName = addrTokens[0];
            // 设置redis配置文件sentinel.conf配置的sentinel别名
            // 哨兵模式之下，设置的集群节点，就是设置的是哨兵自己本身的集群节点地址
            // 哨兵自己为了保证自己是高可用的，他自己也可以是多节点来进行部署
            // 把哨兵别名，设置为了sentinel server的master name
            config.useSentinelServers().setMasterName(sentinelAliasName);
            config.useSentinelServers().setDatabase(database);
            if (StringUtils.isNotBlank(password)) {
                config.useSentinelServers().setPassword(password);
            }
            // 设置sentinel节点的服务IP和端口
            // 从逗号切割后的第二个节点开始，遍历，每个节点地址作为一个哨兵的地址加入进去
            // 哨兵哥儿们会监控你的master+slaves主从架构，哨兵是知道redis完整的集群地址列表
            // redisson来讲的话，只要找哨兵问问，就知道你的额redis master+slaves地址
            for (int i = 1; i < addrTokens.length; i++) {
                config.useSentinelServers().addSentinelAddress(RedissionConstant.REDISSION_CONNECTION_PREFIX.getValue() + addrTokens[i]);
            }
            log.info("初始化[sentinel]方式Config,redisAddress:" + address);
        } catch (Exception e) {
            log.error("sentinel Redisson init error", e);
            e.printStackTrace();
        }
        return config;
    }
}
