package com.athl.gulimall.order.controller;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.TreeMap;

public class revise {
    public static void main(String[] args) {


        long startTime = System.currentTimeMillis();    //获取开始时间

        StringBuffer stringBuffer = new StringBuffer("hhhh");
        System.out.println(stringBuffer.append("aaa"));

        long endTime = System.currentTimeMillis();    //获取结束时间

        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");

        ThreadLocal<StringBuffer> stringBufferThreadLocal = new ThreadLocal<>();
    }
    //单例模式之饿汉
    public static class Singleton{

        private static  final Singleton singleton = new Singleton();

        private Singleton(){}

        public static Singleton getInstance() {
            return singleton;
        }
    }


}
