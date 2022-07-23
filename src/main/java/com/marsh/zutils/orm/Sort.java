package com.marsh.zutils.orm;

import lombok.Getter;
import lombok.Setter;

/**
 * 排序指标类
 *
 * @author lucky.zhou
 */
public class Sort {

    @Getter
    @Setter
    private String sidx;

    @Getter
    private String sord;

    public Sort(String sidx, String sord) {
        this.sidx = sidx;
        this.sord = sord;
    }

    public static Sort of(String sidx) {
        return new Sort(sidx, SordEnum.ASC.name());
    }

    public static Sort of(String sidx, SordEnum sord) {
        return new Sort(sidx, sord.name());
    }
}
