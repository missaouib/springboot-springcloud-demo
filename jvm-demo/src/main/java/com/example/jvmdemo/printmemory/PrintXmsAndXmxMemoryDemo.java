package com.example.jvmdemo.printmemory;

import java.sql.SQLOutput;

/**
 * 通过代码打印-Xms和-Xmx的内存大小
 */
public class PrintXmsAndXmxMemoryDemo {

    public static void main(String[] args) {
        long totalMemory = Runtime.getRuntime().totalMemory();//返回Java虚拟机的内存总量
        long maxMemory = Runtime.getRuntime().maxMemory();//返回Java虚拟机试图使用的最大内存量
        System.out.println("TOTAL_MEMORY(-Xms) = "+totalMemory+" (字节)、"+(totalMemory/(double)1024/1024)+"MB");
        System.out.println("MAX_MEMORY(-Xmx) = "+maxMemory+" (字节)、"+(maxMemory/(double)1024/1024)+"MB");
    }

}
