package com.example.rabbitmqdemo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
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
            connection = connectionFactory.newConnection("消费者");
            // 3 通过连接获取通道 Channel
            channel = connection.createChannel();
            Channel finalChannel = channel;
            // 4 通过创建交换机，声明队列，绑定关系，路由 key，发送消息和接收消息
            channel.basicConsume("queue1", false, new DeliverCallback() {
                public void handle(String s, Delivery delivery) throws IOException {
                    String message = new String(delivery.getBody(), "UTF-8");
                    System.out.println("收到消息是:\t" +message+"===="+Thread.currentThread().getName() );
                    if (message.equals("2")) {
                        //此处抛出异常后，如果不对异常进行处理，将会关闭channel，后续消息都会消费失败
                        throw new RuntimeException("测试消费异常，观察消息是否重新入队到队列头部！");
                    }
                    if (message.equals("3")) {
                        //消息未ack，消息会重新入队到队列头部，不断发送此消息，可能会造成消息积压
                        finalChannel.basicNack(delivery.getEnvelope().getDeliveryTag(),false,true);
                    } else {
                        finalChannel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
                    }
                }
            }, new CancelCallback() {
                public void handle(String s) throws IOException {
                    System.out.println("接收失败");
                }
            });

            System.out.println("开始接收消息");
            System.in.read();
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