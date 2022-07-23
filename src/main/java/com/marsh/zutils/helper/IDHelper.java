package com.marsh.zutils.helper;

import com.marsh.zutils.exception.BaseException;
import com.marsh.zutils.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lucky.zhou
 */
@Component
public class IDHelper {

    private final Snowflake snowflake;

    private static final int ID_MAX_SEQ = 999;
    private static AtomicInteger atomInt;

    static {
        Random random = new Random();
        int rand = random.nextInt(ID_MAX_SEQ);
        atomInt = new AtomicInteger(rand);
    }

    /**
     * 当前机器编号
     */
    @Value("${uid.machId:1}")
    private String machId ;

    public IDHelper(Snowflake snowflake) {
        this.snowflake = snowflake;
    }


    private int getInc(int maxValue) {
        int inc = atomInt.incrementAndGet();
        if (inc >= maxValue) {
            // 最大时，则进入同步逻辑，修改为0
            synchronized (IDHelper.class) {
                if (atomInt.get() >= maxValue) {
                    atomInt.set(0);
                }
                inc = atomInt.incrementAndGet();
            }
        }
        if (inc > maxValue) {
            throw new BaseException("生成序列号，获取当前唯一递增数大于最大值，请检查");
        }
        return inc;
    }

    /**
     * 短ID，在类目中可以使用
     */
    public String generateShortID() {

        StringBuilder sb = new StringBuilder();
        long days = LocalDate.of(2014, 1, 1).until(LocalDate.now(), ChronoUnit.DAYS);
        long seconds = LocalTime.now().toSecondOfDay();
        sb.append(days);
        // 当天秒数，补足5位
        sb.append(String.format("%05d", seconds));
        sb.append(machId);
        // 递增序列号，补足3位
        sb.append(String.format("%03d", getInc(999)));
        return sb.toString();
    }

    public String generateID() {
        if (StringUtils.isBlank(machId)) {
            return UUIDUtil.cleanLowerUUID();
        }

        StringBuilder sb = new StringBuilder();
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        // 日期
        sb.append(date);
        // 当天秒数，补足5位
        sb.append(String.format("%05d", LocalTime.now().toSecondOfDay()));
        // 递增序列号，补足3位
        sb.append(String.format("%03d", getInc(ID_MAX_SEQ)));
        // 机器码
        sb.append(machId);
        sb.append("00");
        return sb.toString();
    }


    /**
     * 生成带前缀的ID
     */
    public String generateIDWithPre(String prefix) {
        return prefix + generateID();
    }

    /**
     * 生成带前缀的短ID
     */
    public String generateShortIDWithPre(String prefix) {
        return prefix + generateShortID();
    }


    public Long uniqueId() {
        return snowflake.nextId();
    }

}
