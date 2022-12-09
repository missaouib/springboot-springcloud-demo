package com.ruyuan.cloud.mq.consumer;

import com.ruyuan.cloud.mq.annotation.MessageQueueListener;
import com.ruyuan.cloud.mq.properties.MessageQueueProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.*;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
@Slf4j
public class RocketMQConsumer {

    private final List<MessageListener> messageListeners;
    private final MessageQueueProperties messageQueueProperties;

    public RocketMQConsumer(MessageQueueProperties messageQueueProperties, List<MessageListener> messageListeners) throws Exception {
        this.messageQueueProperties = messageQueueProperties;
        this.messageListeners = messageListeners;
        initConsumer();
    }

    private void initConsumer() throws Exception {
        // 对于我们的rocketmq consumer来说的，他自己本身就是一个独立的线程会去进行消费
        // 针对每个listener，创建一个独立的rocketmq原生的consumer，让每个原生的consumer消费指定的topic的消息
        // 消费到的一个消息，就会交给我们的listener监听器
        for (MessageListener listener : messageListeners) {
            Class<? extends MessageListener> clazz = listener.getClass();
            MessageQueueListener annotation = clazz.getAnnotation(MessageQueueListener.class);
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(annotation.consumerGroup());
            consumer.setNamesrvAddr(messageQueueProperties.getRocketmq().getServer());
            // tags，在发送消息到topic里去的时候，每个消息是可以带上tags，在broker端进行tags存储
            // 消费的时候可以指定针对tags进行消费，broker端，会筛选出指定的tags对应的消息
            // 拿到tags对应的消息
            consumer.subscribe(annotation.topic(), annotation.tags());
            // MessageListenerConcurrently，他的话呢，其实是属于我们的rocketmq原生带的consumer
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                for (MessageExt messageExt : msgs) {
                    String message = new String(messageExt.getBody());
                    // 进行消息的回调，是我们的自定义的listener回调
                    listener.onMessage(message);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.start();
        }
    }
}
