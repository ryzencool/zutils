package com.marsh.zutils.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListResponse<T> {

    /**
     * 数据列表
     */
    private List<T> dataList;

}
