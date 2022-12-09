package com.ruyuan.cloud.mq.produer;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
public interface MessageQueue {

    /**
     * 发送消息
     *
     * @param topic   topic
     * @param message message
     */
    void send(String topic, String message) throws MessageQueueException;

    /**
     * 发送消息
     *
     * @param topic          topic
     * @param message        message
     * @param tags           RocketMq生效
     */
    void send(String topic, String message, String tags) throws MessageQueueException;

    /**
     * 发送消息
     *
     * @param topic          topic
     * @param message        message
     * @param tags           RocketMq生效
     * @param key            RocketMQ唯一的Key,Kafka用于分区的key
     */
    void send(String topic, String message, String tags, String key) throws MessageQueueException;

    /**
     * 发送消息
     *
     * @param topic          topic
     * @param message        message
     * @param tags           RocketMq生效
     * @param key            RocketMQ唯一的Key,Kafka用于分区的key
     * @param delayTimeLevel 延迟时间，kafka不支持
     */
    void send(String topic, String message, String tags, String key, Integer delayTimeLevel) throws MessageQueueException;
}
