package com.trading.financial.exception;

import lombok.Getter;

@Getter
public enum ErrorEnum {
    ID_NOT_NULL("F001","id could not be null",false),
    UNKNOWN("999","unknown error", false);
    ;

    private String code;
    private String msg;
    private boolean canRetry;

    ErrorEnum(String code, String msg, boolean canRetry) {
        this.code = code;
        this.msg = msg;
        this.canRetry = canRetry;
    }
}
