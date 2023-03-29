package com.athl.gulimall.order.code;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutor {
    public static void main(String[] args) {
        //1.创建了一个包含了5个线程的线程池
        ExecutorService fi = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            fi.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程名称" + Thread.currentThread().getName());
                }
            });
            fi.shutdown();
        }
    }
}