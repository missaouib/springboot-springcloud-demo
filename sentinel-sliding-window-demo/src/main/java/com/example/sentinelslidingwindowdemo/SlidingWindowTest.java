package com.example.sentinelslidingwindowdemo;

import com.example.sentinelslidingwindowdemo.slidingwindow.StatisticNode;
import com.example.sentinelslidingwindowdemo.slidingwindow.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class SlidingWindowTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlidingWindowTest.class);

    public static void main(String[] args) throws Exception {
        StatisticNode node = new StatisticNode();
        //qps限流阈值
        int qpsCount = 10;
        while (true) {
            double curQps = node.passQps( ) + 1;
            if (curQps <= qpsCount) {
                node.addPassRequest(1);

                long startTime = TimeUtil.currentTimeMillis();
                long rt = TimeUtil.currentTimeMillis() - startTime;

                LOGGER.info("执行业务逻辑=============");
                //模拟执行业务逻辑
                Thread.sleep(1500);

                node.addRtAndSuccess(rt, 1);
            } else {
                LOGGER.error("触发限流，curQps:{}",curQps);
            }
        }
    }

}
