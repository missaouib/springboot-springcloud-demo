package com.example.javaconcurrent.volatile_;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * static静态全局变量修改run标识
 */
public class StaticVolatileDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaticVolatileDemo.class);

    //private static volatile boolean run = true;
    private static boolean run = true;

    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (run) {
                    //LOGGER.info("running...");
                    //System.out.println("running...");
                    //System.out.printf("running...");
                    //此处以上三种输出语句都不能写，因为调用的方法都有synchronized，会造成从主存中重新读取变量
                }
                LOGGER.info("感知到run=false============");
            }
        } );
        t.start();

        TimeUnit.SECONDS.sleep(2L);
        run = false;
        LOGGER.info("设置run=false");
    }
}