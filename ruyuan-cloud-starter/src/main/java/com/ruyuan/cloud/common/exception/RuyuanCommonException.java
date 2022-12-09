package com.ruyuan.cloud.common.exception;


import com.ruyuan.cloud.common.api.CommonError;
import com.ruyuan.cloud.common.enums.EmBusinessError;
import lombok.Getter;

/**
 * 异常
 *
 * @author zhonghuashishan
 */
@Getter
public class RuyuanCommonException extends Exception {

    private CommonError commonError;

    public RuyuanCommonException(EmBusinessError emBusinessError) {
        super();
        this.commonError = new CommonError(emBusinessError);
    }

    public RuyuanCommonException(EmBusinessError emBusinessError, String errorMsg) {
        super();
        this.commonError = new CommonError(emBusinessError);
        this.commonError.setErrMsg(errorMsg);
    }

}