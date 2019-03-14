package com.javabasic.tes;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.tes
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/3/14 16:39
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class Test {

    public static char getFirstNoRepeatChar3(String str) {
        // 用HashMap来存储元素和个数
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // 要是集合里有的话 就加一
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                // 没有的话 就存储
                map.put(c, 1);
            }
        }

        // 遍历集合找到值为一的就是要找的元素
        for (int j = 0; j < str.length(); j++) {
            char b = str.charAt(j);
            if (map.get(b) == 1)
                return b;
        }
        throw new RuntimeException("没有找到");
    }

    public static void main(String[] args) {
        System.out.println(getFirstNoRepeatChar3("aasabcdddcc"));
    }




}
