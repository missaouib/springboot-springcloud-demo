package com.ruyuan.cloud.common.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应结果封装
 *
 * @param <T> 响应结果中包含的数据的泛型
 * @author zhonghuashishan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonRes<T> implements Serializable {

    private String status;

    private T data;

    public static <T> CommonRes<T> success(T data) {
        return new CommonRes<>("success", data);
    }

    public static <CommonError> CommonRes<CommonError> fail(CommonError data) {
        return new CommonRes<>("fail", data);
    }

}