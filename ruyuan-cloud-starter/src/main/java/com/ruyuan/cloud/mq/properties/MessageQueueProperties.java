package com.ruyuan.cloud.mq.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.NonLeaked;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
@Data
@ConfigurationProperties(prefix = "ruyuan.mq")
public class MessageQueueProperties {

    private Kafka kafka;
    private RocketMQ rocketmq;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Kafka {
        private Boolean enableProducer;
        private Boolean enableConsumer;
        private String server;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RocketMQ {
        private Boolean enableProducer;
        private Boolean enableConsumer;
        private String server;
        private String producerGroup = "ruyuan-cloud-group";
    }

}
