package com.javabasic.jvm.model;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.jvm.model
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/1/23 10:04
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class Fibonacci {

    //漏洞:未判断n为0的情况
    public static int fibonacci(int n){
        if(n == 0){ return 0; }
        if(n == 1){ return 1; }
        return fibonacci(n -1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(0));
        System.out.println(fibonacci(1));
        System.out.println(fibonacci(2));
        System.out.println(fibonacci(3));
        //递归的层数过多, 就会引起栈内存溢出的情况
        System.out.println(fibonacci(100000));
    }
}
