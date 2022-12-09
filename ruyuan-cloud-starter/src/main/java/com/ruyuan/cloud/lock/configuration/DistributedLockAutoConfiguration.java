package com.ruyuan.cloud.lock.configuration;

import com.ruyuan.cloud.lock.properties.DistributedLockProperties;
import com.ruyuan.cloud.lock.redisson.ClusterRedissonConfig;
import com.ruyuan.cloud.lock.redisson.MasterSlaveRedissonConfig;
import com.ruyuan.cloud.lock.redisson.SentinelRedissonConfig;
import com.ruyuan.cloud.lock.redisson.SingleRedissonConfig;
import com.ruyuan.cloud.lock.template.CuratorDistributedLockTemplate;
import com.ruyuan.cloud.lock.template.DistributedLockTemplate;
import com.ruyuan.cloud.lock.template.RedissonDistributedLockTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分布式锁的自动装配
 *
 * @author zhonghuashishan
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(DistributedLockProperties.class)
public class DistributedLockAutoConfiguration {

    @Bean
    @ConditionalOnProperty(value = "ruyuan.distributed.lock.redisWorkMode", havingValue = "standalone")
    public Config singleRedissonConfig(DistributedLockProperties distributedLockProperties) {
        return SingleRedissonConfig.createRedissonConfig(distributedLockProperties);
    }

    // master，挂在多个slave，master数据异步化的同步和复制到slave去
    // 他master如果宕机了，slave不会自动化的切换过去的
    // 去实现master+slave的读写分离，通过多个slave去分摊读并发压力
    @Bean
    @ConditionalOnProperty(value = "ruyuan.distributed.lock.redisWorkMode", havingValue = "master-slave")
    public Config masterSlaveRedissonConfig(DistributedLockProperties distributedLockProperties) {
        return MasterSlaveRedissonConfig.createRedissonConfig(distributedLockProperties);
    }

    // sentinel节点，监控master和slaves，看看如果master死了，负责故障转移，slave转移为master
    @Bean
    @ConditionalOnProperty(value = "ruyuan.distributed.lock.redisWorkMode", havingValue = "sentinel")
    public Config sentinelRedissonConfig(DistributedLockProperties distributedLockProperties) {
        return SentinelRedissonConfig.createRedissonConfig(distributedLockProperties);
    }

    // 一般来说都是用cluster mode，多个master，每个master可以有slaves，如果master死了，slave自动切换为master
    @Bean
    @ConditionalOnProperty(value = "ruyuan.distributed.lock.redisWorkMode", havingValue = "cluster")
    public Config clusterRedissonConfig(DistributedLockProperties distributedLockProperties) {
        return ClusterRedissonConfig.createRedissonConfig(distributedLockProperties);
    }

    @Bean
    @ConditionalOnClass(Redisson.class)
    @ConditionalOnProperty(value = "ruyuan.distributed.lock.type", havingValue = "redis")
    public DistributedLockTemplate redisDistributedLockTemplate(Config config) {
        // redis基础操作的客户端，jedis，主要是提供的redis开源的操作
        // 对于企业级的缓存开发，redisson就可以了，他底层会基于jedis封装，开发和提供分布式锁在内的企业级的功能
        // 分布式锁的话，通常来说的话，都是通过redisson来实现的
        // zk也是有开源的框架，ZookeeperClient，我们可以使用企业级框架，curator，提供了很多企业级的功能
        // 之前的config装配，仅仅是针对单机、主从、哨兵、集群四种模式下，进行config里的redis地址设置
        // 我们真正构建redisson client的时候，针对不同的部署模式创建redisson client
        RedissonClient redissonClient = Redisson.create(config);
        // 对外封装的一套API就是我们的RedissonDistributedLockTemplate
        // 实现DistributedLockTemplate这个接口的
        return new RedissonDistributedLockTemplate(redissonClient); // redisson client封装DistributedLockTemplate
    }

    @Bean
    @ConditionalOnClass(CuratorFramework.class)
    @ConditionalOnProperty(value = "ruyuan.distributed.lock.type", havingValue = "zk")
    public DistributedLockTemplate curatorDistributedLockTemplate(DistributedLockProperties distributedLockProperties) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                distributedLockProperties.getAddress(),
                retryPolicy);
        client.start();
        // 实现DistributedLockTemplate接口
        // 把redisson和curator两个框架分布式锁对外的API接口，通过我的接口进行统一
        return new CuratorDistributedLockTemplate(client);
    }

}
