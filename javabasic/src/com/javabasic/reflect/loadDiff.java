package com.javabasic.reflect;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.reflect
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/1/22 16:31
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class loadDiff {
    public static void main(String[] args) throws Exception {
        //获取Robot的classLoader, 直接运行, 发现什么都没有打印出来, 说明Robot未加载
        //ClassLoader cl = Robot.class.getClassLoader();


        /////////////
        //Hello Robot打印出来了. 这说明forName已经初始化了这个类.
        Class r = Class.forName("com.javabasic.reflect.Robot");


    }
}
