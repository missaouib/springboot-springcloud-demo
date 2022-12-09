package com.ruyuan.cloud.lock.template;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.TimeUnit;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
@Slf4j
@AllArgsConstructor
public class CuratorDistributedLockTemplate implements DistributedLockTemplate {

    private CuratorFramework curatorFramework;

    @Override
    public DistributeLock getLock(String name) {
        if (!name.startsWith("/")) {
            throw new IllegalArgumentException("zk加锁名称不合法");
        }
        InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, name);
        return new DistributeLock() {
            @Override
            public boolean lock() throws Exception {
                interProcessMutex.acquire();
                return true;
            }

            @Override
            public boolean lock(int time, TimeUnit timeUnit) throws Exception {
                return interProcessMutex.acquire(time, timeUnit);
            }

            @Override
            public void unlock() {
                try {
                    if (interProcessMutex.isAcquiredInThisProcess()) {
                        interProcessMutex.release();
                    }
                } catch (Exception e) {
                    log.error("Distributed lock unlock failed. ", e);
                }
            }
        };
    }
}
