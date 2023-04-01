package com.athl.gulimall.order.Steram;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

//聚合（max/min/count)
public class StreamTest4 {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        Optional<String> max = list.stream().max(Comparator.comparing(String::length));
        System.out.println("最长的字符串"+max.get());

        //获取Integer集合中的最大值。
        List<Integer> list2 = Arrays.asList(7, 6, 9, 4, 11, 6);
        // 自然排序
        Optional<Integer> max1 = list2.stream().max(Integer::compareTo);
        //自定义排序（从小到大）
        Optional<Integer> max2 = list2.stream().max(((o1, o2) -> o2 - o1));
        System.out.println("自然排序的最大值："+max1.get());
        System.out.println("自定义排序的最大值：" + max2.get());


        //计算Integer集合中大于6的元素的个数。
        long count = list2.stream().filter(x -> x > 6).count();
        System.out.println("list中大于6的元素个数：" + count);


    }
}
