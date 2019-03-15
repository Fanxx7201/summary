package com.javabasic.pub;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.pub
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/3/15 9:48
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class ProdAndCust {

    public static void main(String[] args) {
        final Box bigBox = new Box();
        for (int i = 0; i < 500; ++i) {
            new Thread(new Runnable() {
                public void run() {
                    Produ pro = new Produ(bigBox);
                    pro.put();
                }
            }).start();
        }
        for (int i = 0; i < 500; ++i) {
            new Thread(new Runnable() {
                public void run() {
                    Cust cus = new Cust(bigBox);
                    cus.get();
                }
            }).start();
        }
    }
}
