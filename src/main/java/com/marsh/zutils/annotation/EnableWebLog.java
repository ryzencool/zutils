package com.marsh.zutils.annotation;

import com.marsh.zutils.log.InterceptorConfig;
import com.marsh.zutils.log.LogAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({InterceptorConfig.class, LogAspect.class})
@Documented
public @interface EnableWebLog {
}
