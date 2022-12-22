package com.example.kafkacanal;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerDemo.class);

    private static final LoggerContext defaultLoggerContext = (LoggerContext)StaticLoggerBinder.getSingleton().getLoggerFactory();

    static {
        //修改日志级别
        defaultLoggerContext.getLogger(Logger.ROOT_LOGGER_NAME).setLevel(Level.INFO);
    }

    public static void main(String[] args) {
        KafkaConsumer<String,String> consumer = createConsumer();
        consumer.subscribe(Arrays.asList("test"));

        while(true) {
            try {
                ConsumerRecords<String,String> records = consumer.poll(Integer.MAX_VALUE);
                for (ConsumerRecord<String,String> record : records) {
                    System.out.println(record.value());
                }
            } catch (Exception e) {
                e.printStackTrace();
                consumer.close();
            }
        }
    }

    private static KafkaConsumer<String,String> createConsumer() {
        Properties props = new Properties();

        props.put("bootstrap.servers","192.168.3.57:9092");
        props.put("group.id","test-group");
        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("heartbeat.interval.ms",1000);
        props.put("session.timeout.ms",10*1000);
        props.put("max.poll.interval.ms",30*1000);
        props.put("fetch.max.bytes",10485760);
        props.put("max.poll.records",500);
        props.put("connection.max.idle.ms",-1);
        props.put("enable.auto.commit","true");
        props.put("auto.commit.interval.ms","1000");
        props.put("auto.offset.reset","earliest");

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(props);

        return consumer;
    }

}
