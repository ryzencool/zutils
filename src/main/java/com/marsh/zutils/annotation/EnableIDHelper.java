package com.marsh.zutils.annotation;


import com.marsh.zutils.helper.IDHelper;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({IDHelper.class})
@Documented
public @interface EnableIDHelper {
}
