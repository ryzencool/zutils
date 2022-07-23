package com.marsh.zutils.enums;

import lombok.Getter;

public enum GenderEnum {
    MAN(1), WOMAN(2), UNKNOWN(0);

    @Getter
    private final Integer type;

    GenderEnum(Integer type) {
        this.type = type;
    }
}
