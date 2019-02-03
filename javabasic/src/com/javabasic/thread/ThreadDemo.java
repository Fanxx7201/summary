package com.javabasic.thread;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.thread
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/2/3 20:19
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class ThreadDemo {
    public static void main(String[] args) {
        //线程1,2,3交替执行, 达到了多线程的目的
        MyThread mt1 = new MyThread("Thread1");
        MyThread mt2 = new MyThread("Thread2");
        MyThread mt3 = new MyThread("Thread3");
        mt1.start();
        mt2.start();
        mt3.start();
    }
}
