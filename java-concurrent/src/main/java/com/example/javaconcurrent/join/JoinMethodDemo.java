package com.example.javaconcurrent.join;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * join方法测试：当运行线程执行完毕的时候，jvm会调用notifyAll方法自动唤醒阻塞线程
 */
public class JoinMethodDemo {

    public static void main(String[] args) {
        ExecutorService executorPool = Executors.newFixedThreadPool(2);
        executorPool.submit(new Task("task1",1000));
        executorPool.submit(new Task("task2",1000));
        executorPool.submit(new Task("task3",1000));
    }

    static class Task implements Runnable {

        private String name;

        private long millSeconds;

        public Task(String name,long millSeconds) {
            this.name = name;
            this.millSeconds = millSeconds;
        }

        @Override
        public void run() {
            try {
                ThreadMap.put(name,Thread.currentThread());
                Thread.sleep(millSeconds);
                if (Objects.equals(name,"task1")) {
                    Thread.sleep(millSeconds);
                }
                if (Objects.equals(name,"task2")) {
                    ThreadMap.get("task1").join(5000);
                }
                System.out.println(name +"=======doSomething==================");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadMap {

        private static Map<String,Thread> threadMap = new HashMap<>();

        public static void put(String name,Thread thread) {
            threadMap.put(name,thread);
        }

        public static Thread get(String name) {
            return threadMap.get(name);
        }
    }
}
