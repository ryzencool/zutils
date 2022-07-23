package com.marsh.zutils.entity;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> extends ListResponse<T> {

    /**
     * 当前页码，第一页为0
     */
    private long pageNum;

    /**
     * 每页条数
     */
    private long pageSize;

    /**
     * 总条数
     */
    private long totalCount;

    public PageResponse(long pageNum, long pageSize, long totalCount, List<T> dataList) {
        super(dataList);
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    public static <T> PageResponse<T> of(long pageNum, long pageSize, long totalCount, List<T> dataList) {
        return new PageResponse<>(pageNum, pageSize, totalCount, dataList);
    }

    public static <T> PageResponse<T> of(IPage<T> page) {
        return new PageResponse<T>(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    }
}