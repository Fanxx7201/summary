package com.javabasic.pub;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.pub
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/3/15 9:30
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class Produ {

    private Box mybox;

    //构造函数
    Produ(Box b){
        this.mybox = b;
    }

    public void put(){
        Apple apple = new Apple();
        mybox.put(apple);
    }
}
