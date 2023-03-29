package com.athl.gulimall.order.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class ThreadLocalTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    private static final Logger logger = LoggerFactory.getLogger(ThreadLocalTest.class);

    public static void main(String[] args) {
            int arr[] = {23, 42, 11, 89, 77, 30, 10};
            logger.info(Arrays.toString(arr));
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = 0; j < arr.length - 1 - i; j++) {
                    if (arr[j]>arr[j+1]){
                        int temp =arr[j+1];
                        arr[j+1]=arr[j];
                        arr[j]=temp;
                    }
                }
            }
            logger.info(Arrays.toString(arr));
        //   创建第一个线程
        Thread threadA = new Thread(() -> {
            threadLocal.set("ThreadA" + Thread.currentThread().getName());
            System.out.println("线程A本地变量中的值为：" + threadLocal.get());
            threadLocal.remove();
            System.out.println("线程A删除本地变量后ThreadLocal中的值为：" + threadLocal.get());
        });
        //创建第二个线程
        Thread threadB = new Thread(() -> {
            threadLocal.set("ThreadB：" + Thread.currentThread().getName());
            System.out.println("线程B本地变量中的值为：" + threadLocal.get());
            System.out.println("线程B没有删除本地变量：" + threadLocal.get());
        });
        //启动线程A和线程B
        threadA.start();
        threadB.start();

    }


}
