package com.wq.andoidlearning.sort;

public class FastSort {


    public static void sort1(int[] list) {
        //冒泡排序
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length - i - 1; j++) {
                if (list[j] > list[j + 1]) {
                    int temp = list[j + 1];
                    list[j + 1] = list[j];
                    list[j] = temp;
                }
            }
        }
    }

    public static void sort2(int[] list){
        //快速排序
    }
}
