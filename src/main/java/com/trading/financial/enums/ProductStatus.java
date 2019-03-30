package com.trading.financial.enums;

import lombok.Getter;

@Getter
public enum ProductStatus {
    AUDITING("AUDITING"),
    IN_SELL("IN SELL"),
    LOCKED("SELLING LOCKED"),
    FINISHED("FINISHED")
    ;

    private String desc;

    ProductStatus(String desc) {
        this.desc = desc;
    }
}
