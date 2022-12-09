package com.ruyuan.cloud.excel.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * easy-poi相关属性
 *
 * @author zhonghuashishan
 */
@Data
@ConfigurationProperties(prefix = "ruyuan.easy.poi")
public class EasyPoiProperties {

    /**
     * 是否开启easy poi组件
     */
    private boolean enable = false;

    /**
     * 不存在会默认创建 导入excel时数据的存储路径
     */
    private String importExcelStorePath;

}