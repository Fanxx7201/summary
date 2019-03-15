package com.javabasic.pub;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.pub
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/3/15 9:34
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class Cust {
    private Box mybox;

    Cust(Box b){
        this.mybox = b;
    }

    public Apple get(){
        return mybox.get();
    }
}
