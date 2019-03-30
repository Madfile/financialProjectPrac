package com.trading.financial.enums;

public enum OrderStatus {
    INIT("INIT"),
    PROCESS("PROCESSING"),
    SUCCESS("SUCCESS"),
    FAIL("FAIL")
    ;

    private String desc;

    OrderStatus(String desc) {
        this.desc = desc;
    }
}
