package com.marsh.zutils.enums;

import lombok.Getter;

/**
 * 内部服务
 */
public enum ClientTypeEnum {

    ORDER(1), VIP(2), CARD(3);

    @Getter
    private final Integer type;

    ClientTypeEnum(Integer type) {
        this.type = type;
    }
}
