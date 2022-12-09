package com.ruyuan.cloud.mq.produer;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
public class MessageQueueException extends Exception{
    public MessageQueueException() {
    }

    public MessageQueueException(String message) {
        super(message);
    }

    public MessageQueueException(Exception e) {
        super(e);
    }
}
