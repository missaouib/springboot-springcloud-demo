package com.ruyuan.cloud.common.api;

import com.ruyuan.cloud.common.enums.EmBusinessError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 错误封装
 *
 * @author zhonghuashishan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonError implements Serializable {

    /**
     * 错误码
     */
    private Integer errCode;
    /**
     * 错误描述
     */
    private String errMsg;

    public CommonError(EmBusinessError emBusinessError) {
        this.errCode = emBusinessError.getErrCode();
        this.errMsg = emBusinessError.getErrMsg();
    }

}