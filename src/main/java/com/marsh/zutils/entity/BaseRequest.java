package com.marsh.zutils.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marsh.zutils.orm.Sort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lucky.zhou
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseRequest {

    //    页码，默认：0
    private Integer pageNum = 0;

    //    页长，默认：20
    private Integer pageSize = 20;

    //    排序，有待商榷，因为可能造成数据库性能问题
    private List<Sort> sorts;


    public <T> Page<T> page() {
        return new Page<T>().setCurrent(pageNum).setSize(pageSize);
    }
}
