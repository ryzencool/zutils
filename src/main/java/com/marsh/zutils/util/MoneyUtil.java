package com.marsh.zutils.util;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class MoneyUtil {

    public static int decimalToInt(BigDecimal money) {
        return money.multiply(BigDecimal.valueOf(100)).setScale(0, RoundingMode.HALF_UP).intValue();
    }

    public static BigDecimal intToDecimal(Integer money) {
        return BigDecimal.valueOf(money).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }
}
