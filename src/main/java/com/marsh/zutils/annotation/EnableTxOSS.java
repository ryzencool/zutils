package com.marsh.zutils.annotation;


import com.marsh.zutils.config.TxOSSConfig;
import com.marsh.zutils.storage.TxOSSStorage;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({TxOSSConfig.class, TxOSSStorage.class})
@Documented
public @interface EnableTxOSS {
}
