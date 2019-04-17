package com.javabasic.Thr;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.Thr
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/3/15 10:00
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class ThreadB extends Thread {

    private MyObject object;

    public ThreadB(MyObject object){
        this.object = object;
    }

    @Override
    public void run(){
        super.run();
        object.methodB();
    }
}
