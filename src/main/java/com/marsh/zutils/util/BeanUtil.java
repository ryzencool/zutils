package com.marsh.zutils.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * bean转化工具类
 */
@Slf4j
public class BeanUtil {

    private BeanUtil() {

    }

    public static <T> T transfer(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            T t = targetClass.newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (InstantiationException | IllegalAccessException e) {

            return null;
        }
    }


    public static <T> List<T> transfer(List<?> sourceList, Class<T> targetClass) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return new ArrayList<T>();
        }
        return sourceList.stream().map((source) -> transfer(source, targetClass)).collect(Collectors.toList());
    }


    public static <T> Map<String, Object> beanToMap(T bean) {
        ObjectMapper om = new ObjectMapper();
        return om.convertValue(bean, new TypeReference<Map<String, Object>>() {
        });
    }


    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        List<Map<String, Object>> list = Lists.newArrayList();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (T t : objList) {
                bean = t;
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz)
            throws InstantiationException, IllegalAccessException {
        List<T> list = Lists.newArrayList();
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (Map<String, Object> stringObjectMap : maps) {
                map = stringObjectMap;
                bean = clazz.newInstance();
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
    }


}

