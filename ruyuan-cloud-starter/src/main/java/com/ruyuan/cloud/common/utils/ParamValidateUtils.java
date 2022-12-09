package com.ruyuan.cloud.common.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * 参数校验工具类
 *
 * @author zhonghuashishan
 */
public class ParamValidateUtils {

    /**
     * 获取校验结果
     *
     * @param bindingResult 入参绑定结果
     * @return 错误信息
     */
    public static String processErrorString(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return "";
        }
        StringBuffer result = new StringBuffer();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            result.append(fieldError.getDefaultMessage()).append(",");
        }
        return result.substring(0, result.length() - 1);
    }

}