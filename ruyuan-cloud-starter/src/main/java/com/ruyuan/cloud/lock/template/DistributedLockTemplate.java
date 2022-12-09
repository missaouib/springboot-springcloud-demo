package com.ruyuan.cloud.lock.template;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
public interface DistributedLockTemplate {
    /**
     * 获取一个分布式锁对象
     *
     * @param name 锁名称
     * @return 分布式锁
     */
    DistributeLock getLock(String name);
}
