package com.athl.gulimall.order.Steram;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//遍历/匹配（foreach/find/match）
public class StreamTest1 {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        // 遍历输出符合条件的元素
        //使用 “对象::实例方法名” 的语法糖
        list.stream().filter(x->x>6).forEach(System.out::println);
        //匹配第一个
        Optional<Integer> first = list.stream().filter(x -> x > 6).findFirst();
        //匹配任意
        Optional<Integer> any = list.stream().parallel().filter(x -> x > 6).findAny();
        Optional<Integer> any1 = list.parallelStream().filter(x -> x < 6).findAny();
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x > 6);
        System.out.println("匹配第一个值：" + first.get());
        System.out.println("匹配任意一个值：" + any.get());
        System.out.println("匹配任意一个值第二种写法：" + any1.get());
        System.out.println("是否存在大于6的值：" + anyMatch);
    }
}
