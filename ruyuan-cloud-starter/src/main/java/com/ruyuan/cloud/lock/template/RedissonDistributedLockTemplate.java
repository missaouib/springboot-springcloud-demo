package com.ruyuan.cloud.lock.template;

import lombok.AllArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
@AllArgsConstructor
public class RedissonDistributedLockTemplate implements DistributedLockTemplate {

    private RedissonClient redissonClient;

    @Override
    public DistributeLock getLock(String name) {
        RLock fairLock = redissonClient.getFairLock(name);
        // 生成了接口的匿名内部类的实例对象
        return new DistributeLock() {
            @Override
            public boolean lock() {
                return fairLock.tryLock();
            }

            @Override
            public boolean lock(int time, TimeUnit timeUnit) throws InterruptedException {
                return fairLock.tryLock(time, 1, timeUnit);
            }

            @Override
            public void unlock() {
                if (fairLock.isHeldByCurrentThread()) {
                    fairLock.unlock();
                }
            }
        };
    }
}
