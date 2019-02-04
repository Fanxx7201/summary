package com.javabasic.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.thread
 * @Description: 通过Callable接口实现: 通过FutureTask
 * @Author: fanxx
 * @CreateDate: 2019/2/3 22:58
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //future中传入callable
        FutureTask<String> task = new FutureTask<String>(new MyCallable());
        new Thread(task).start();
        if(!task.isDone()){
            System.out.println("task has not finished, please wait!");
        }
        //task.get() 获取线程返回值
        System.out.println("task return: " + task.get());

    }
}
