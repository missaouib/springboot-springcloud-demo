package com.example.jvmdemo.mat;

import java.util.ArrayList;
import java.util.List;

/**
 * mat分析例子
 *
 * 查看进程pid：jps -l
 * 堆内存转储：jmap -dump:live,format=b,file=dump.hprof 53948
 * 查看jvm内存使用状况：jmap -heap
 * 查看jvm内存存活的对象：jmap -histo:live
 * 把heap里所有对象都dump下来，无论对象是死是活：jmap -dump:format=b,file=xxx.hprof
 * 先做一次full GC，再dump，只包含仍然存活的对象信息：jmap -dump:live,format=b,file=xxx.hprof
 */
public class MATDumpAnalysisDemo {

    public static void main(String[] args) throws Exception {
        List<Data> datas = new ArrayList<>();
        for(int i=0;i < 10000;i++) {
            datas.add(new Data());
        }
        Thread.sleep(1*60*60*1000);
    }

    static class Data {
        //private byte[] bytes = new byte[1*1024*1024];
    }

}
