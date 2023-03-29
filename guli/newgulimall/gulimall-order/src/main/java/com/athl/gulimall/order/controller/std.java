package com.athl.gulimall.order.controller;

import com.google.common.util.concurrent.RateLimiter;

import org.junit.Test;

import java.util.Arrays;

public class std {
    public static void main(String[] args) {
        int[] arr = {11, 22, 33, 44, 55, 66, 77};//数组中的数（由小到大）
        System.out.println(Arrays.toString(arr));
        int key = 77;
        System.out.println("\nThe key to be searched:" + key);
        int result = Arrays.binarySearch(arr, key);
        if (result < 0)
            System.out.println("\nKey is not found in the array!");
        else
            System.out.println("\nKey is found at index: " + result + " in the array.");
        //定义需要查询的值
        int select_value = 66;
        int index = getIndex(arr, select_value);
        System.out.println("查询值所对应的索引为：" + index);

        System.out.println(sum(99));

    //斐波那契递归计算
        System.out.println(Fribonacci(9));
    }
    public static int Fribonacci(int n){
        if(n <= 2)
            return 1;
        else
            return Fribonacci(n-1)+Fribonacci(n-2);
    }
    public static  int sum(int n) {
        if (n == 1) {
            return 1;
        } else {
            return sum(n - 1) + n;
        }
    }
    public static int getIndex(int[] arr, int select_value) {

        //初始化最小值的索引为0
        int left = 0;
        //初始化最大值的索引为arr.length-1
        int right = arr.length - 1;
        //首尾相加再除以2得出中间索引
        int mid = (left + right) >>> 1;
        while (left <= right) {//确保程序不会重复查询，不会越界
            if (select_value > arr[mid]) {
                left = mid + 1;
            } else if (select_value < arr[mid]) {
                right = mid - 1;
            } else {
                return mid;
            }
            mid = (left + right) / 2;
        }
        return -1;
    }

    //令牌限流方案实例
    //RateLimiter的create方法来创建实例，每秒生成5个令牌
    private RateLimiter rateLimiter = RateLimiter.create(5);

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(200l);
            boolean b = rateLimiter.tryAcquire();
            if (b) {
                System.out.println("拿到了令牌");
            }
        }
    }

}
