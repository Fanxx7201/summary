package com.javabasic.thread;

import java.util.concurrent.Callable;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.thread
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/2/3 22:50
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        String value = "test";
        System.out.println("Ready to work");
        Thread.currentThread().sleep(5000);
        System.out.println("task done");
        return value;
    }
}
