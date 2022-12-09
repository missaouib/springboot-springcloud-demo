package com.ruyuan.cloud.mq.consumer;


/**
 * @author zhonghuashishan
 * @version 1.0
 */
public interface MessageListener {

    /**
     * 处理消息
     *
     * @param message         消息
     */
    default void onMessage(String message){
        onMessage(message, () -> {});
    }

    /**
     * 处理消息
     *
     * @param message         消息
     * @param acknowledgement 提交offset,处理完消息一定要提交
     */
    void onMessage(String message, Acknowledgement acknowledgement);
}
