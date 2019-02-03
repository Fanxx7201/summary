package com.javabasic.thread;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.thread
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/2/3 20:24
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class RunnableDemo {

    /**
     * @Description  Runnable没有start()方法, 所以只有创建thread, 然后将runnable传递进去才可以.
     * @Date  2019/2/3
     * @Param [args]
     * @return void
     **/
    public static void main(String[] args) {
        MyRunnable mr1 = new MyRunnable("Runnable1");
        MyRunnable mr2 = new MyRunnable("Runnable2");
        MyRunnable mr3 = new MyRunnable("Runnable3");
        Thread t1 = new Thread(mr1);
        Thread t2 = new Thread(mr2);
        Thread t3 = new Thread(mr3);
        t1.start();
        t2.start();
        t3.start();
    }
}
