package com.javabasic.tes;

/**
 * @ProjectName: sum
 * @Package: com.javabasic.tes
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/3/14 17:53
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class Test2 {

    public static int dup(int []array) {

        // TODO Auto-generated method stub
        if(array == null || array.length <= 0){
            return -1;
        }
        for(int i=0;i<array.length;i++){ //判断输入数组的合法性
            if(array[i] < 0 || array[i] >= array.length){
                return -1;
            }
        }
        for(int i=0;i<array.length;i++){
            if(i != array[i] && array[i] == array[array[i]]){ //重复数据
                return array[i];
            }
            while(i != array[i]){ //将数字与第m个位置上的数字交换
                exchange(array, i, array[i]);
            }
        }
        return -1;

    }

    public static void exchange(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {1,3,4,2,1};
        int duplicate = dup(array);
        System.out.println("重复的数字为: " + duplicate);
    }
}
