package com.javabasic.thread;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.thread
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/2/3 22:20
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class CycleWait implements Runnable {
    private String value;
    @Override
    public void run(){
        try{
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        value = "we have data now";
    }

    public static void main(String[] args) throws InterruptedException {
        CycleWait cw = new CycleWait();
        Thread t = new Thread(cw);
        t.start();

        //主线程等待法, 一直死循环, 等待获得线程的返回值.
        /*while(cw.value == null){
            Thread.currentThread().sleep(100);
        }*/
        t.join(); //取代主线程的等待.
        //主线程如果不等待的话, 这时打印的value值是Null.
        System.out.println("value: " + cw.value);
    }
}
