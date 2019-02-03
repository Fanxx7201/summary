package com.javabasic.thread;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.thread
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/2/3 20:15
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class MyThread extends Thread {

    private String name;

    public MyThread(String name){
        this.name = name;
    }

    @Override
    public void run(){
        for(int i = 0; i < 10; i++){
            System.out.println("Thread start: " + this.name + ", i=" + i);
        }
    }
}
