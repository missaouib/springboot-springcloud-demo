package com.example.javaconcurrent.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * SynchronousQueue和其他的BlockingQueue不同的是SynchronousQueue的capacity是0。
 * 即SynchronousQueue不存储任何元素。
 *
 * 也就是说SynchronousQueue的每一次insert操作，必须等待其他线性的remove操作。
 * 而每一个remove操作也必须等待其他线程的insert操作
 */
public class SynchronousQueueDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(SynchronousQueueDemo.class);

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        SynchronousQueue<Object> synchronousQueue=new SynchronousQueue<>();

        Runnable producer = () -> {
            Object object=new Object();
            try {
                LOGGER.info("produced execute before!");
                synchronousQueue.put(object);
                LOGGER.info("produced execute after!");
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage(),ex);
            }

        };

        Runnable consumer = () -> {
            try {
                LOGGER.info("consumed execute before!");
                Object object = synchronousQueue.take();
                LOGGER.info("consumed execute after!");
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage(),ex);
            }
        };

        executor.submit(producer);
        executor.submit(consumer);

        executor.awaitTermination(50000, TimeUnit.MILLISECONDS);
        executor.shutdown();
    }
}
