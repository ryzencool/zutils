package com.marsh.zutils.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author lucky.zhou
 */
public class UUIDUtil {

    private UUIDUtil() {
    }

    public static String cleanUpperUUID() {
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }

    public static String cleanLowerUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成6位校验码
     */
    public static String checkCode() {
        int num = new Random().nextInt(999999);
        return String.format("%06d", num);
    }
}
