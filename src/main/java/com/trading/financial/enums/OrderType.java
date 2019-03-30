package com.trading.financial.enums;

import lombok.Getter;

@Getter
public enum OrderType {
    APPLY("APPLY"),
    REDEEM("REDEEM")
    ;

    private String desc;

    OrderType(String desc) {
        this.desc = desc;
    }
}
