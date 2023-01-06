package com.example.javaconcurrent.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    Object data;

    volatile boolean cacheValid;

    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void processCacheData() {
        rwl.readLock().lock();
        if (!cacheValid) {
            rwl.readLock().unlock();
            rwl.writeLock().lock();

            try {
                if (!cacheValid) {
                    data = null;//TODO
                    cacheValid = true;
                }
                //降级为读锁
                rwl.readLock().lock();
            } finally {
                rwl.writeLock().unlock();
            }
        }

        try {
            use(data);
        } finally {
            rwl.readLock().unlock();
        }
    }


    public void use(Object data) {
        //TODO
    }

}
