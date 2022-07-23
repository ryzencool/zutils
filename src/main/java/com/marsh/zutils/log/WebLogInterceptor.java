package com.marsh.zutils.log;

import com.alibaba.fastjson.JSONObject;
import com.marsh.zutils.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 日志配置类
 *
 * @author lucky
 * @version 1.0
 */
public class WebLogInterceptor implements HandlerInterceptor {

    public static final String UUID_KEY = "uuId";
    public static final String INVOKE_TIME_KEY = "invokeTime";
    public static final String BEGIN_TIME_KEY = "beginTime";
    public static final String COST_TIME_KEY = "costTime";
    public static final String METHOD_KEY = "methodName";
    public static final String PARAMS_KEY = "paramValues";
    public static final String RESULT_KEY = "resultValue";
    public static final String EXCEPTION_MSG_KEY = "exceptionMsg";
    public static final String REQUEST_REMOTE_HOST_MDC_KEY = "senderHost";
    public static final String REQUEST_USER_AGENT_MDC_KEY = "userAgent";
    public static final String REQUEST_REQUEST_URI = "req.requestURI";
    public static final String REQUEST_QUERY_STRING = "req.queryString";
    public static final String REQUEST_PARAMETER_MAP = "req.parameterMap";

    public static final String REQUEST_REQUEST_URL = "req.requestURL";
    public static final String REQUEST_X_FORWARDED_FOR = "req.xForwardedFor";
    public static final String LOGED_FLAG = "$LOGED_FLAG$";
    private static final Logger log = LoggerFactory.getLogger(WebLogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        addMdc(request);
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        calcCostTime();
        if (Objects.equals(MDC.get(REQUEST_REQUEST_URI).trim(), "/")) {
            return;
        }
        log.info("uuid:{} requestURI:{} senderHost:{} paramValue:{} paramMap:{} queryString:{} result:{} costTime:{} method:{} userAgent:{}",
                MDC.get(UUID_KEY),
                MDC.get(REQUEST_REQUEST_URI),
                MDC.get(REQUEST_REMOTE_HOST_MDC_KEY),
                MDC.get(PARAMS_KEY),
                MDC.get(REQUEST_PARAMETER_MAP),
                MDC.get(REQUEST_QUERY_STRING),
                MDC.get(RESULT_KEY),
                MDC.get(COST_TIME_KEY),
                MDC.get(METHOD_KEY),
                MDC.get(REQUEST_USER_AGENT_MDC_KEY));
        MDC.put(LOGED_FLAG, String.valueOf(true));
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null || MDC.get(LOGED_FLAG) == null) {
            calcCostTime();
            if (ex != null) {
                MDC.put(EXCEPTION_MSG_KEY, ex.getLocalizedMessage());
            }
        }
        clearMdc();
    }

    private void addMdc(HttpServletRequest request) {
        MDC.put(INVOKE_TIME_KEY, LocalDateTime.now().toString());
        MDC.put(UUID_KEY, UUIDUtil.cleanLowerUUID());
        MDC.put(BEGIN_TIME_KEY, String.valueOf(System.currentTimeMillis()));
        MDC.put(METHOD_KEY, request.getMethod());
        MDC.put(REQUEST_REMOTE_HOST_MDC_KEY, request.getRemoteHost());
        MDC.put(REQUEST_REQUEST_URI, request.getRequestURI());
        StringBuffer sb = request.getRequestURL();
        if (sb != null) {
            MDC.put(REQUEST_REQUEST_URL, sb.toString());
        }
        MDC.put(REQUEST_PARAMETER_MAP, JSONObject.toJSONString(request.getParameterMap()));
        MDC.put(REQUEST_QUERY_STRING, request.getQueryString());
        MDC.put(REQUEST_USER_AGENT_MDC_KEY, request.getHeader("User-Agent"));
        MDC.put(REQUEST_X_FORWARDED_FOR, request.getHeader("X-Forwarded-For"));
    }

    private void clearMdc() {
        MDC.remove(INVOKE_TIME_KEY);
        MDC.remove(UUID_KEY);
        MDC.remove(BEGIN_TIME_KEY);
        MDC.remove(METHOD_KEY);
        MDC.remove(COST_TIME_KEY);
        MDC.remove(REQUEST_REMOTE_HOST_MDC_KEY);
        MDC.remove(REQUEST_REQUEST_URI);
        MDC.remove(REQUEST_QUERY_STRING);
        MDC.remove(REQUEST_PARAMETER_MAP);

        MDC.remove(REQUEST_REQUEST_URL);
        MDC.remove(REQUEST_USER_AGENT_MDC_KEY);
        MDC.remove(REQUEST_X_FORWARDED_FOR);
        MDC.remove(EXCEPTION_MSG_KEY);
    }

    private void calcCostTime() {
        long costTime = -1l;
        try {
            costTime = System.currentTimeMillis() - Long.valueOf(MDC.get(BEGIN_TIME_KEY));
        } catch (Exception e) {
            // ignore
        }
        MDC.put(COST_TIME_KEY, String.valueOf(costTime));
    }


}
