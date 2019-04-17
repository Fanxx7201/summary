package com.javabasic.Thr;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.Thr
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/3/15 10:02
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class Run {

    public static void main(String[] args) {
        MyObject object = new MyObject();

        ThreadA a = new ThreadA(object);
        ThreadB b = new ThreadB(object);
        a.start();
        b.start();
    }
}
