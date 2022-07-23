package com.marsh.zutils.util;

import com.marsh.zutils.constant.CharConstant;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * string 工具类
 */
public class StringUtil {

    private StringUtil() {

    }

    public static <T> String joinWithComma(List<T> elements) {
        if (!CollectionUtils.isEmpty(elements)) {
            return elements.stream().map(Object::toString).collect(Collectors.joining(CharConstant.COMMA));
        } else {
            return CharConstant.EMPTY_STRING;
        }
    }

    public static int[] splitIdsWithComma(String src) {
        return Arrays.stream(src.split(CharConstant.COMMA)).mapToInt(Integer::valueOf).toArray();
    }

    public static String[] splitStrWithComma(String src) {
        return src.split(CharConstant.COMMA);

    }

    public static <T> String joinWithComma(T... elements) {
        if (elements != null && elements.length > 0) {
            return Arrays.stream(elements).map(Object::toString).collect(Collectors.joining(CharConstant.COMMA));
        } else {
            return CharConstant.EMPTY_STRING;
        }
    }


}
