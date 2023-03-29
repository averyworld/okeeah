package com.athl.gulimall.product.service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestArray {
        public static void main(String[] args) {
            List<Integer> list = new CopyOnWriteArrayList<>();
            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                new Thread(() -> {
                    list.add(random.nextInt(10));
                    System.out.println(list);
                }).start();
            }
        }
    }

