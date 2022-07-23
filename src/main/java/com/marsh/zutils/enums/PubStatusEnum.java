package com.marsh.zutils.enums;

import lombok.Getter;

/**
 * 上下架状态
 */
public enum PubStatusEnum {
    /**
     * 上架
     */
    UP(1),

    /**
     * 下架
     */
    DOWN(0);

    @Getter
    private final int status;

    PubStatusEnum(int status) {
        this.status = status;
    }


}
