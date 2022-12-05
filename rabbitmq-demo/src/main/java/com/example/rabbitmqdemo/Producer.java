package com.example.rabbitmqdemo;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws Exception {
        // 所有的中间件技术都是基于 TCP/IP 协议基础之上构建的协议规范，只不过 rabbitmq 遵循的是 AMQP 协议
        // ip port
        // 1 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.3.57");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        Connection connection = null;
        Channel channel = null;
        try {
            // 2 创建连接 Connection
            connection = connectionFactory.newConnection("生产者");
            // 3 通过连接获取通道 Channel
            channel = connection.createChannel();
            // 4 通过创建交换机，声明队列，绑定关系，路由 key，发送消息和接收消息
            String queueName = "queue1";
            /*
             * 队列名字
             * 是否具有持久化 durable=false 所谓持久化消息是否存盘，如果是false 非持久化 true 持久化？非持久化会存盘么，会存盘，但是会随着服务器的重启丢失
             * 排他性，是否具有独立线程
             * 是否自动删除，随着最后一个消费者消息消费完毕之后，是否把队列自动删除
             * 携带一些附属参数
             */
            channel.queueDeclare(queueName,false,false,false,null);
            String exchangeName = "test-direct-exchange";
            //声明交换机
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT.getType(),true);
            //声明路由键
            String routingKey = "message-route";
            //绑定关系
            channel.queueBind(queueName,exchangeName,routingKey);

            for (int i=10;i < 20;i++) {
                String message = i+"";
                // 6 发送消息给队列 queue
                // 交换机，路由key，消息是否持久化，消息内容主体
                channel.basicPublish(exchangeName,routingKey,null,message.getBytes());
                System.out.println("发送消息成功："+i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            // 7 关闭通道
            if (channel!= null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            // 8 关闭连接
            if (connection != null && connection.isOpen()) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}