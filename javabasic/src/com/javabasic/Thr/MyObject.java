package com.javabasic.Thr;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.Thr
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/3/15 9:56
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class MyObject {

    synchronized public void methodA(){
        System.out.println("methodA:doSomething");
    }

    synchronized public void methodB(){
        System.out.println("methodB:doSomething");
    }
}
