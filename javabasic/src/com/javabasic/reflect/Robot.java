package com.javabasic.reflect;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.reflect
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/1/22 10:30
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class Robot {
    private String name;

    public void sayHi(String helloSentence){
        System.out.println(helloSentence + " " + name);
    }

    private String throwHello(String tag){
        return "hello" + tag;
    }
}
