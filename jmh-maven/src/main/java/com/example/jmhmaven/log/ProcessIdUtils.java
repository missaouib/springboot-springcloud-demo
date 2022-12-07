package com.example.jmhmaven.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class ProcessIdUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(BenchMarkDemoTest.class);

    public static Integer logProcessId(String methodName) {
       RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        Integer pid = Integer.valueOf(runtimeMXBean.getName().split("@")[0]);
        LOGGER.info("调用方法：{},当前线程：{},进程pid：{}",methodName,Thread.currentThread().getName(),pid);
        return pid;
    }

}
