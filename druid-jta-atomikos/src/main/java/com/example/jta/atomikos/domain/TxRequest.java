package com.example.jta.atomikos.domain;

import java.io.Serializable;

public class TxRequest implements Serializable {

    private static final long serialVerionUID = 1L;

    private Boolean creditError;

    private Boolean lotteryError;

    public Boolean getCreditError() {
        return creditError;
    }

    public void setCreditError(Boolean creditError) {
        this.creditError = creditError;
    }

    public Boolean getLotteryError() {
        return lotteryError;
    }

    public void setLotteryError(Boolean lotteryError) {
        this.lotteryError = lotteryError;
    }
}
