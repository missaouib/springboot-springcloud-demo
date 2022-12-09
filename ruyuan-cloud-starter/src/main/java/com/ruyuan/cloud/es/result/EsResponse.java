package com.ruyuan.cloud.es.result;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

/**
 * es查询结果
 *
 * @author zhonghuashishan
 * @version 1.0
 */
@Data
public class EsResponse {

    private long total;

    private JSONArray data;
}