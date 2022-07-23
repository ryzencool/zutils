package com.marsh.zutils.orm;

import lombok.Data;

import java.util.List;

/**
 * 树状结构容器
 *
 * @param <T>
 * @author lucky.zhou
 */
@Data
public class TreeContainer<T extends Tree> {

    private T tree;

    /**
     * 子对象列表
     */
    private List<TreeContainer<T>> children;

    public TreeContainer(T tree) {
        this.tree = tree;
    }
}
