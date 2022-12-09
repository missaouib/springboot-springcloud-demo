package com.ruyuan.cloud.mq.consumer;


/**
 * @author zhonghuashishan
 * @version 1.0
 */
public interface Acknowledgement {

    /**
     * 提交offset
     */
    void ack();

}
