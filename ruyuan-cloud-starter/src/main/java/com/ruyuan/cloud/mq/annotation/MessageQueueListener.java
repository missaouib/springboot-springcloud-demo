package com.ruyuan.cloud.mq.annotation;

import com.ruyuan.cloud.mq.MessageQueueType;

import java.lang.annotation.*;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageQueueListener {

    /**
     * @return topic
     */
    String topic();

    /**
     * 消费者组
     *
     * @return 消费者组
     */
    String consumerGroup() default "default-consumer-group";

    /**
     * 类型
     *
     * @return 类型
     */
    MessageQueueType type() default MessageQueueType.UNKNOWN;

    /**
     * 消费tags
     *
     * @return tags
     */
    String tags() default "*";
}
