package com.marsh.zutils.log;

import com.alibaba.fastjson.JSONObject;
import com.marsh.zutils.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.MDC;

public class LogUtil {

    public static void initTrace() {
        MDC.put("traceId", UUIDUtil.cleanLowerUUID());
    }

    public static void logCycleInit(Long sn, Integer socketNo) {
        MDC.put("sn", String.valueOf(sn));
        MDC.put("socket", String.valueOf(socketNo));
    }

    public static void logCycle(Logger log, String msg, Object param) {
        log.info("traceId:[{}] sn:[{}] socket:[{}] orderNo:[{}] " + msg + ":[{}]",
                MDC.get("traceId"), MDC.get("sn"), MDC.get("socket"), MDC.get("orderNo"), JSONObject.toJSONString(param));
    }

    public static void logCommon(Logger log, String msg, Object param) {
        log.info("traceId:[{}] " + msg + ":[{}]", MDC.get("traceId"), JSONObject.toJSONString(param));
    }

    public static void setOrder(Long orderNo) {
        MDC.put("orderNo", orderNo.toString());
    }

    public static void logOrderEnd(Logger log, String msg, Object param) {
        log.info("traceId:[{}] orderNo:[{}]" + msg + ":[{}]", MDC.get("traceId"), MDC.get("orderNo"), JSONObject.toJSONString(param));
    }

}
