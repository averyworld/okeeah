package com.athl.gulimall.order.code;

import java.util.Random;

public class Lucky {
    public static void main(String[] args) {
        test1();
    }
        public static void test1(){
            String[] namesArray = {"王小刚","李思思","杨倩倩","李大勇","姚期名","陶冰冰"};
            int length = namesArray.length;
            // 进行1次随机选择
            Random random = new Random();
            int number = random.nextInt(length);
            String name = namesArray[number];
            System.out.println("被选中的是："+name);
        }
    }
