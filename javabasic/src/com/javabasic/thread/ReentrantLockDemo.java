package com.javabasic.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.thread
 * @Description: 公平锁和非公平锁的对比
 * @Author: fanxx
 * @CreateDate: 2019/2/5 21:48
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class ReentrantLockDemo implements Runnable{
    //参数设置为true, 公平锁, 每个线程获取锁的概率是一样的.
    //参数设置为false, 非公平, 每个线程获取锁的概率不一样.
    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true){
            try{
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " get lock ");
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo rtld = new ReentrantLockDemo();
        Thread thread1 = new Thread(rtld);
        Thread thread2 = new Thread(rtld);
        thread1.start();
        thread2.start();
    }
}
