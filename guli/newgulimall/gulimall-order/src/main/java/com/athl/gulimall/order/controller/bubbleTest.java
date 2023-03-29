package com.athl.gulimall.order.controller;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

public class bubbleTest implements Runnable{
    public static void main(String[] args) {
        int[] arr={7,19,9,23,1,3};
        //外层循环控制排序躺数
        for (int i = 0; i < arr.length-1; i++) {
            //内层循环控制每次排序的次数
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j]>arr[j+1]){
                    int temp= arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName());
    }
}
