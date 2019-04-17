package com.javabasic.Thr;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.Thr
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/3/15 9:58
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class ThreadA extends Thread {


    private MyObject object;

    //构造函数
    public ThreadA(MyObject object) {
        this.object = object;
    }
    @Override
    public void run(){
        super.run();
        object.methodA();
    }


}
