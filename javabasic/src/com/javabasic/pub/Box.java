package com.javabasic.pub;

import java.util.ArrayList;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.pub
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/3/15 9:31
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class Box {

    private ArrayList<Apple> boxlist = new ArrayList<Apple>();

    static int capacity = 50;

    public void put(Apple apple){
        synchronized (this) {

            try {
                while (boxlist.size() >= capacity) {
                    wait();
                }
                boxlist.add(apple);
            } catch (Exception e) {
                e.printStackTrace();
            }
            notifyAll();
            System.out.println("put finished! Now the box has "
                    + boxlist.size() + " apples!");
        }
    }

    public Apple get(){
        Apple result = null;
        synchronized (this){
            try{
                while (boxlist.size() <= 0){
                    wait();
                }
                result = boxlist.remove(boxlist.size() - 1);
            }catch (Exception e){
                e.printStackTrace();
            }
            notifyAll();
            System.out.println("get finished! Now the box has "
                    + boxlist.size() + " apples!");
        }
        return result;
    }
}
