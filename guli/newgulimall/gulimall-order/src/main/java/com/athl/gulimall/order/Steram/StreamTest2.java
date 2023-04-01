package com.athl.gulimall.order.Steram;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

//筛选（filter） 筛选，是按照一定的规则校验流中的元素，将符合条件的元素提取到新的流中的操作
public class StreamTest2 {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(6, 7, 3, 8, 1, 2, 9);
        Stream<Integer> stream = list.stream();
        stream.filter(x -> x > 7).forEach(System.out::println);
    }
}