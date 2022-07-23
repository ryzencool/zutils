package com.marsh.zutils.log;

import com.marsh.zutils.exception.BaseException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志过滤器
 *
 * @author lucky
 * @version 1.0
 */
@Aspect
public class LogAspect {


    @Pointcut("execution(* com.zekun.*.*.com.anchong.charging.api.item.service.web..*.*(..))")
    public void controller() {
    }


    @Around("controller()")
    public Object around(ProceedingJoinPoint point) {
        Object o = null;
        try {
            Object[] args = point.getArgs();
            if (args.length > 0) {
                Class classes = args[0].getClass();
                Field[] fields = classes.getDeclaredFields();
                Map<String, Object> map = new HashMap<>(16);
                for (Field field : fields) {
                    field.setAccessible(true);
                    map.put(field.getName(), field.get(args[0]));
                }
                MDC.put(WebLogInterceptor.PARAMS_KEY, map.toString());
            }

            o = point.proceed();
            Class resCls = o.getClass();
            Field[] resFields = resCls.getDeclaredFields();
            Map<String, Object> resMap = new HashMap<>(16);
            for (Field f : resFields) {
                f.setAccessible(true);
                resMap.put(f.getName(), f.get(o));
            }
            MDC.put(WebLogInterceptor.RESULT_KEY, resMap.toString());
        } catch (BaseException ex) {
            throw ex;
        } catch (Throwable e) {
            // ignore
        }
        return o;
    }
}
