package com.ruyuan.cloud.es.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * es属性
 *
 * @author zhonghuashishan
 */
@Data
@ConfigurationProperties(prefix = "ruyuan.es")
public class EsProperties {

    /**
     * 是否启用
     */
    private boolean enable;
    /**
     * es 连接地址集合
     */
    private List<String> clusterNodes = new ArrayList<>(Collections.singletonList("localhost:9200"));

    /**
     * 建立连接超时时间 毫秒
     */
    private int connectionTimeoutMillis = 1000;
    /**
     * 数据传输过程中的超时时间
     */
    private int socketTimeoutMillis = 30000;
    /**
     * 从连接池获取连接的超时时间
     */
    private int connectionRequestTimeoutMillis = 500;

    /**
     * 路由节点的最大连接数
     */
    private int maxConnPerRoute = 10;
    /**
     * client最大连接数量
     */
    private int maxConnTotal = 30;
}
