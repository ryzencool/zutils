package com.marsh.zutils.util;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolUtil {



    public static ThreadPoolExecutor initIO(String name) {
        int count = Runtime.getRuntime().availableProcessors();
        return new ThreadPoolExecutor(count,
                4 * count,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(count * 2),
                new CustomThreadFactory(name),
                new ThreadPoolExecutor.DiscardOldestPolicy());
    }



}

class CustomThreadFactory implements ThreadFactory {
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    CustomThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix+"-";
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread( r,namePrefix + threadNumber.getAndIncrement());
        if (t.isDaemon())
            t.setDaemon(true);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}