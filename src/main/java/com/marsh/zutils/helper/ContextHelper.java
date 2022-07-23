package com.marsh.zutils.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ContextHelper implements ApplicationContextAware {

    public static ApplicationContext APPLICATION_CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ContextHelper.APPLICATION_CONTEXT = applicationContext;
    }

    public static <T> T getBean(Class<T> clz) {
        return (T)APPLICATION_CONTEXT.getBean(clz);
    }
}
