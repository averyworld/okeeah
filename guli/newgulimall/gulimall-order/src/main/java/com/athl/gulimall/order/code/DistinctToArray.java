package com.athl.gulimall.order.code;

import java.util.ArrayList;
import java.util.Scanner;

public class DistinctToArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //初始化输入的数字
        int n=scanner.nextInt();
        //创建一个数组存放需要去重的元素
        int[] arr=new int[n];
        //循环读取数组元素
        for (int i = 0; i <n ; i++) {
            arr[i]=scanner.nextInt();
        }
        //创建一个list数组来去重
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int v:arr) {
            if (!list.contains(v)) {
                list.add(v);
            }
        }
            for (int success:list){
                System.out.print(success+" ");
            }
        }
    }

