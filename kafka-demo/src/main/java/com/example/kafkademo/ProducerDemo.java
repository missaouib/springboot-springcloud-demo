package com.example.kafkademo;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Properties;

public class ProducerDemo {

    public static void main(String[] args) {
        KafkaProducer<String,String> producer = createKafkaProducer();

        String user = "{\"id\":1,\"username\":\"李四2\"}";

        ProducerRecord<String,String> record = new ProducerRecord<>("test","user",user);

        //异步发送
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception == null) {
                    System.out.println("消息发送成功！");
                } else {
                    System.out.println("消息发送失败！");
                }
            }
        });

        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        producer.close();
    }


    private static KafkaProducer<String,String> createKafkaProducer() {
        Properties props = new Properties();

        props.put("bootstrap.servers","192.168.3.57:9092");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("buffer.memory",67108864);
        props.put("batch.size",131072);
        props.put("linger.ms",100);
        props.put("max.request.size",10485760);
        props.put("acks","1");
        //一般来说，重试3~5次即可
        props.put("retries",10);
        //每次重试间隔
        props.put("retry.backoff.ms",500);

        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(props);

        return producer;
    }

}
