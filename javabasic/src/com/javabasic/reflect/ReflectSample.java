package com.javabasic.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.reflect
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/1/22 10:37
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class ReflectSample {
    public static void main(String[] args) throws Exception {
        //获取反射类
        Class rc = Class.forName("com.javabasic.reflect.Robot");
        //创建Robot的实例
        Robot r = (Robot)rc.newInstance();
        System.out.println("Class name:" + rc.getName());
        //public方法可以直接调用
        r.sayHi("你好");

        //private方法不能直接调用.
        //getDeclaredMethod能获取到所有的方法.不能获取继承的方法和实现接口的方法
        Method getHello = rc.getDeclaredMethod("throwHello", String.class);
        //默认的accessible是false
        getHello.setAccessible(true);
        //反射调用方法
        Object str = getHello.invoke(r, "传入参数");
        System.out.println("result" + str);


        //另一种反射调用方法
        Method sayHi = rc.getMethod("sayHi", String.class);
        sayHi.invoke(r, "Welcome");
        Field name = rc.getDeclaredField("name");
        name.setAccessible(true);
        name.set(r, "Alice");
        sayHi.invoke(r, "Welcome");
    }
}
