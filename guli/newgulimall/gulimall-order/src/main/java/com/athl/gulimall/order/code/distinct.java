package com.athl.gulimall.order.code;

import java.util.Arrays;
import java.util.HashSet;

public class distinct {
    public static void main(String[] args) {
        HashSet<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 6));
        HashSet<Integer> set2 = new HashSet<>(Arrays.asList(1, 6));
        if (!set1.isEmpty()|set2.isEmpty()){
            set1.retainAll(set2);
            System.out.println(set1);
        }
    }
}
