package com.ruyuan.cloud.lock.template;

import java.util.concurrent.TimeUnit;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
public interface DistributeLock {

    /**
     * 阻塞加锁
     */
    boolean lock() throws Exception;

    /**
     * 加锁
     * @param time 超时时间
     * @param timeUnit 超时时间单位
     */
    boolean lock(int time, TimeUnit timeUnit) throws Exception;

    /**
     * 释放锁
     */
    void unlock();
}
