package com.javabasic.tes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.tes
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/3/15 9:19
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class Qishui {




        public static int solution(int n) {
            int sum = 0;
            if (n <= 1) { //一个瓶子不能换
                sum = 0;
            } else { //两个以上可以换
                sum = n / 2; //直接除以2，因为int类型所以多一个瓶子也不影响
            }

            return sum;
        }

        public static void main(String[] args) {
            List<Integer> inputs = new ArrayList<Integer>();
            Scanner scan = new Scanner(System.in);
            System.out.println("输入:");
            while (true) {
                int input = scan.nextInt();
                System.out.println("输入为:" + input);
                if (0 == input) {
                    break;
                } else {
                    inputs.add(input);
                }
            }
            scan.close();

            for (int input : inputs) {
                int number = solution(input);
                System.out.println(number);
            }
        }

}
