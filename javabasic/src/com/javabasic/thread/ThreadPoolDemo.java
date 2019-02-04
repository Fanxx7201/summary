package com.javabasic.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.thread
 * @Description: 通过线程池去获取线程的返回值.
 * @Author: fanxx
 * @CreateDate: 2019/2/4 12:27
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        Future<String> future = newCachedThreadPool.submit(new MyCallable());
        if(!future.isDone()){
            System.out.println("task has not finished, please wait!");
        }
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            newCachedThreadPool.shutdown();
        }
    }
}
