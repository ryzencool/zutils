package com.marsh.zutils.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 反射工具类
 *
 * @author lucky.zhou
 */
public class ReflectUtil {

    private ReflectUtil() {

    }

    public static <T extends Annotation> T getClassAnnotation(final Class<?> classToCheck, final Class<T> annoClass) {
        final Set<Class> visited = new HashSet<>();
        final LinkedList<Class> stack = new LinkedList<>();
        stack.add(classToCheck);

        while (!stack.isEmpty()) {
            Class classToChk = stack.pop();
            if (classToChk == null || visited.contains(classToChk)) {
                continue;
            }
            visited.add(classToChk);
            T a = (T) classToChk.getAnnotation(annoClass);
            if (a != null) {
                return a;
            }
            stack.push(classToChk.getSuperclass());
            addInterfaces(classToChk, stack);
        }
        return null;
    }


    private static void addInterfaces(final Class<?> classToCheck, final LinkedList<Class> stack) {
        for (Class interFace : classToCheck.getInterfaces()) {
            stack.push(interFace);
        }
    }


    public static <T extends Annotation> T getMethodAnnotation(final Method method, final Class<T> annoClass) {
        final Set<Class> visited = new HashSet<>();
        final LinkedList<Class> stack = new LinkedList<>();
        stack.add(method.getDeclaringClass());

        while (!stack.isEmpty()) {
            Class classToChk = stack.pop();
            if (classToChk == null || visited.contains(classToChk)) {
                continue;
            }
            visited.add(classToChk);
            Method m = getMethod(classToChk, method.getName(), method.getParameterTypes());
            if (m == null) {
                continue;
            }
            T a = m.getAnnotation(annoClass);
            if (a != null) {
                return a;
            }
            stack.push(classToChk.getSuperclass());
            addInterfaces(method.getDeclaringClass(), stack);
        }
        return null;
    }


    public static Method getMethod(Class<?> c, String method, Class<?>... types) {
        try {
            return c.getMethod(method, types);
        } catch (Exception nse) {
            return null;
        }
    }

}
