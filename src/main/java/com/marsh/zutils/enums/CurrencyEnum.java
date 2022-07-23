package com.marsh.zutils.enums;

import lombok.Getter;

public enum CurrencyEnum {

    CNY(1);

    @Getter
    private final Integer type;

    CurrencyEnum(Integer type) {
        this.type = type;
    }
}
