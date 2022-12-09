package com.ruyuan.cloud.mq.consumer;

import com.ruyuan.cloud.mq.annotation.MessageQueueListener;
import com.ruyuan.cloud.mq.properties.MessageQueueProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
@Slf4j
public class KafkaConsumer {

    private final List<MessageListener> messageListeners;
    private final MessageQueueProperties messageQueueProperties;

    public KafkaConsumer(MessageQueueProperties messageQueueProperties, List<MessageListener> messageListeners) {
        this.messageQueueProperties = messageQueueProperties;
        this.messageListeners = messageListeners;
        initConsumer();
    }

    private void initConsumer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", messageQueueProperties.getKafka().getServer());
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("enable.auto.commit", "false"); // 让kafka原生消费者，自动去提交offset，禁止了
        properties.put("auto.commit.interval.ms", "1000"); // 如果你要是开启自动提交offset，设置每隔多少时间提交一次
        properties.put("auto.offset.reset", "latest"); // 消费的时候，默认是从最近的一条消息开始进行消费
        if (messageListeners == null || messageListeners.isEmpty()) {
            return;
        }

        // 对我们自己手动实现和定义的每个listener，都会开一个线程，那个线程的话，他会启动一个kafka consumer
        // 不断的通过consumer拉取消息，拉取到的消息会传递给我们的listener回调
        // 再对这条消息去做一个ack
        for (MessageListener messageListener : messageListeners) {
            Processor processor = new Processor(messageListener, properties);
            processor.start();
        }
    }

    private String topic(MessageListener messageListener){
        Class<? extends MessageListener> clazz = messageListener.getClass();
        MessageQueueListener annotation = clazz.getAnnotation(MessageQueueListener.class);
        return annotation.topic();
    }

    private boolean isRun() {
        return true;
    }

    private class Processor extends Thread {

        private final MessageListener messageListener;

        private final org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer;

        public Processor(MessageListener messageListener, Properties properties) {
            this.messageListener = messageListener;
            Iterator<Map.Entry<Object, Object>> iterator = properties.entrySet().iterator();
            Properties copyProperties = new Properties();
            while (iterator.hasNext()){
                Map.Entry<Object, Object> next = iterator.next();
                copyProperties.put(next.getKey(),next.getValue());
            }
            copyProperties.put("group.id", "business-group");
            // 构建出原生的kafka consumer
            this.consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(copyProperties);
            setDaemon(true);
            String topic = topic(messageListener);
            setName("Kafka-Processor-Topic-" + topic);
            // 让原生的kafka consumer可以去订阅一个topic
            this.consumer.subscribe(Collections.singleton(topic));
        }

        @Override
        public void run() {
            while (isRun()) {
                try {
                    // 通过原生的kafka consumer，poll，拉取，设置一个poll timeout的时间
                    // 就是在1s之内，如果说没有拉取到任何消息，此时就直接timeout超时就出来了，下一轮循环就可以了
                    ConsumerRecords<String, String> records = this.consumer.poll(Duration.ofSeconds(1));
                    if (records == null || records.isEmpty()) {
                        continue;
                    }
                    // default max poll 500 message
                    Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>(500);
                    for (ConsumerRecord<String, String> record : records) { // 遍历
                        String value = record.value();
                        // 拿出来一条消息，同时需要保存一下这条消息的offset
                        // kafka的message offset管理和提交，针对topic->partitions->写入消息，每个消息按照顺序排列的
                        // 每条消息都是有自己的offset偏移量，代表着说这条消息在这个partition里的位置
                        // 消费的时候，拿到的每条消息，都是有kafka的partition里的消息offset
                        offsets.put(new TopicPartition(record.topic(), record.partition()),
                                new OffsetAndMetadata(record.offset() + 1));
                        // 基于kafka原生consumer去执行了commit sync，把你保存的offset位置去做了一个提交
                        // 意思就是说告诉kafka，当前我作为一个consumer，把这个offset位置对应的消息
                        // 已经处理完毕了，ok了，你下回不要重复的再给我了
                        // 如果说某个消息过了很长时间，老是没有commit offset，此时会导致这条消息被进行重复消费
                        messageListener.onMessage(value, () -> this.consumer.commitSync(offsets));
                        offsets.clear();
                    }
                } catch (Exception e) {
                    log.error("消费消息产生错误：" + e);
                }
            }
        }
    }
}
