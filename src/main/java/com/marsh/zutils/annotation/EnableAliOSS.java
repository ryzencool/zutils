package com.marsh.zutils.annotation;

import com.marsh.zutils.config.AliOSSConfig;
import com.marsh.zutils.storage.AliOSSStorage;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({AliOSSConfig.class, AliOSSStorage.class})
@Documented
public @interface EnableAliOSS {
}
