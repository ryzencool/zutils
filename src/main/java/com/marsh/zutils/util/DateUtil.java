package com.marsh.zutils.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * @author lucky.zhou
 */
public class DateUtil {

    public static final String BASIC_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String LINK_DATE_TIME_PATTERN = "yyyyMMddHHmmss";


    private DateUtil() {

    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static LocalDateTime parse(String format) {
        return LocalDateTime.parse(format, DateTimeFormatter.ofPattern(BASIC_DATE_TIME_PATTERN));
    }

    public static LocalDateTime parse(String format, String pattern) {
        return LocalDateTime.parse(format, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime timeStampToDateTime(Long ts) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(ts),
                TimeZone.getDefault().toZoneId());
    }

    public static long toMilli(LocalDateTime time) {
        return time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

}
