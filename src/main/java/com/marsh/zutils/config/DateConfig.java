package com.marsh.zutils.config;

import com.marsh.zutils.constant.BaseConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 全局时间转化配置
 */
public class DateConfig {

    @Bean
    public Converter<String, LocalDateTime> LocalDateTimeConvert() {
        return source -> {

            DateTimeFormatter df = DateTimeFormatter.ofPattern(BaseConstant.DATE_TIME_PATTERN);
            LocalDateTime date = null;
            try {
                date = LocalDateTime.parse(source, df);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return date;
        };
    }

    @Bean
    public Converter<String, LocalTime> localTimeConvert() {
        return source -> {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(BaseConstant.TIME_PATTERN);
            LocalTime time = null;
            try {
                time = LocalTime.parse(source, df);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return time;
        };
    }


    @Bean
    public Converter<String, LocalDate> localDateConvert() {
        return source -> {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(BaseConstant.DATE_PATTERN);
            LocalDate time = null;
            try {
                time = LocalDate.parse(source, df);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return time;
        };
    }


}
