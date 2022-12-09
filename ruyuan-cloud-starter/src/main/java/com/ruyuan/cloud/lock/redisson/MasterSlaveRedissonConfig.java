package com.ruyuan.cloud.lock.redisson;

import com.ruyuan.cloud.lock.enums.RedissionConstant;
import com.ruyuan.cloud.lock.properties.DistributedLockProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.config.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * redisson主从配置
 * 格式: 127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381
 * 第一个是主节点，后面两个是从节点
 *
 * @author zhonghuashishan
 */
@Slf4j
public class MasterSlaveRedissonConfig {

    public static Config createRedissonConfig(DistributedLockProperties redissonProperties) {
        Config config = new Config();
        try {
            // 我们可以用多个地址加上逗号分隔
            // redis://xx.xx.xx.xx:xxx,xx.xx.xx.xx:xxx,xx.xx.xx.xx:xxx
            String address = redissonProperties.getAddress();
            String password = redissonProperties.getPassword();
            int database = redissonProperties.getRedisDatabase();
            String[] addrTokens = address.split(",");
            String masterNodeAddr = addrTokens[0]; // 切割以后的第一个地址 ，就是master地址，第二个以及第n个就是slave地址
            // 设置主节点ip
            config.useMasterSlaveServers().setMasterAddress(masterNodeAddr);
            if (StringUtils.isNotBlank(password)) {
                config.useMasterSlaveServers().setPassword(password);
            }
            config.useMasterSlaveServers().setDatabase(database);
            // 设置从节点，移除第一个节点，默认第一个为主节点
            List<String> slaveList = new ArrayList<>();
            for (String addrToken : addrTokens) {
                slaveList.add(RedissionConstant.REDISSION_CONNECTION_PREFIX.getValue() + addrToken);
            }
            slaveList.remove(0);

            config.useMasterSlaveServers().addSlaveAddress((String[]) slaveList.toArray());
            log.info("初始化[MASTERSLAVE]方式Config,redisAddress:" + address);
        } catch (Exception e) {
            log.error("MASTERSLAVE Redisson init error", e);
            e.printStackTrace();
        }
        return config;
    }
}
