package com.example.kafkademo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.lang.reflect.Array;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerDemo {

    public static void main(String[] args) {
        KafkaConsumer<String,String> consumer = createConsumer();
        consumer.subscribe(Arrays.asList("test"));

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

    private static KafkaConsumer<String,String> createConsumer() {
        Properties props = new Properties();

        props.put("bootstrap.servers","localhost:9092");
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
