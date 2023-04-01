package com.athl.gulimall.order.Steram;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//映射(map/flatMap)
public class StreamTest5 {
    public static void main(String[] args) {
        //将每个元素大写
        String[] strArr = {"abcd", "bcdd", "defde", "fTr"};
        List<String> collect = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());

        //将每个元素进行加3
        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());
        System.out.println("将每个元素大写: "+ collect);
        System.out.println("每个元素+3：" + intListNew);

    }
}