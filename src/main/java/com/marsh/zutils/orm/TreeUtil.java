package com.marsh.zutils.orm;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * 数工具类
 *
 * @author lucky.zhou
 */
public class TreeUtil {

    public static final Integer TREE_ROOT_ID = 1;

    /**
     * 树状化根
     *
     * @param treeList
     * @param <T>
     * @return
     */
    public static <T extends Tree> TreeContainer<T> treeFormat(List<T> treeList, Object rootId) {

        TreeContainer<T> root = null;
        Map<Object, List<TreeContainer<T>>> contentMap = new HashMap<>();
        for (T tree : treeList) {
            if (tree.getTreeId().equals(rootId)) {
                root = new TreeContainer<>(tree);
            } else {
                List<TreeContainer<T>> list = contentMap.getOrDefault(tree.getParentId(), new ArrayList<>());
                TreeContainer<T> treeContainer = new TreeContainer<>(tree);
                list.add(treeContainer);
                contentMap.putIfAbsent(tree.getParentId(), list);
            }
        }

        if (root == null) {
            throw new IllegalArgumentException(rootId + "找不到根");
        }

        // 获取所有子对象
        root.setChildren(queryAllChildren(contentMap, rootId));
        return root;
    }

    /**
     * 树状化List
     *
     * @param treeList
     * @param <T>
     * @return
     */
    public static <T extends Tree> List<TreeContainer<T>> treeFormatList(List<T> treeList, Object rootId) {

        Map<Object, List<TreeContainer<T>>> contentMap = new HashMap<>();
        treeList.forEach(tree -> {
            List<TreeContainer<T>> list = contentMap.getOrDefault(tree.getParentId(), new ArrayList<>());
            TreeContainer<T> treeContainer = new TreeContainer<>(tree);
            list.add(treeContainer);
            contentMap.putIfAbsent(tree.getParentId(), list);
        });

        // 获取所有子对象
        return queryAllChildren(contentMap, rootId);
    }


    /**
     * 查找类目下面所有的叶子节点
     *
     * @param <T>
     * @param items
     * @return
     */
    public static <T extends Tree> List<Object> listLeaves(List<T> items) {
        Map<Object, Integer> map = new HashMap<>();
        for (T item : items) {
            if (!map.containsKey(item.getTreeId())) {
                map.put(item.getTreeId(), 1);
            } else {
                map.put(item.getTreeId(), map.get(item.getTreeId()) + 1);
            }
            if (!map.containsKey(item.getParentId())) {
                map.put(item.getParentId(), 1);
            } else {
                map.put(item.getParentId(), map.get(item.getParentId()) + 1);
            }
        }
        return map.entrySet().stream()
                .filter((it -> it.getValue() == 1))
                .map(Entry::getKey)
                .collect(Collectors.toList());

    }


    /**
     * 获取父编号下面所有的子对象
     *
     * @param contentMap
     * @param parentId
     * @param <T>
     * @return
     */
    private static <T extends Tree> List<TreeContainer<T>> queryAllChildren(Map<Object, List<TreeContainer<T>>> contentMap, Object parentId) {
        List<TreeContainer<T>> children = contentMap.getOrDefault(parentId, new ArrayList<>());
        children.forEach(tree -> {
            List<TreeContainer<T>> list = new ArrayList<>();
            list.addAll(queryAllChildren(contentMap, tree.getTree().getTreeId()));
            tree.setChildren(list);
        });
        return children;
    }


    /**
     * 遍历树
     */
    public static <T extends Tree> void readTree(TreeContainer<T> tree, List<T> list) {
        if (CollectionUtils.isEmpty(tree.getChildren())) {
            return;
        } else {
            for (TreeContainer<T> child : tree.getChildren()) {
                list.add(child.getTree());
                readTree(child, list);
            }
        }
    }

    /**
     * 查询某个节点的所有父亲
     *
     * @param origin  所有的节点
     * @param curNode 当前的节点
     * @param root    根节点
     * @param res     返回结果存储
     */
    public static <T extends Tree> void queryParents(List<T> origin, T curNode, T root, List<T> res) {
        if (curNode.getTreeId().equals(root.getTreeId())) {
            return;
        } else {
            origin.parallelStream().filter(it -> it.getTreeId().equals(curNode.getParentId())).findFirst().ifPresent(t -> {
                res.add(t);
                queryParents(origin, t, root, res);
            });
        }
    }
}
