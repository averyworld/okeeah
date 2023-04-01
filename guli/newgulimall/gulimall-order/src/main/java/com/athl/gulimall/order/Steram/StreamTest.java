package com.athl.gulimall.order.Steram;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class StreamTest {
    public static void main(String[] args) {

        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23,"male", "New York"));
        personList.add(new Person("Jack", 7000, 32,"male", "Washington"));
        personList.add(new Person("Lily", 7800, 44,"female", "Washington"));
        personList.add(new Person("Anni", 8200, 13,"female", "New York"));
        personList.add(new Person("Owen", 9500, 76,"male", "New York"));
        personList.add(new Person("Alisa", 7900,33, "female", "New York"));
    }
}
     @Getter
     @Setter
     @AllArgsConstructor
     class Person {
        private String name;  // 姓名
        private int salary; // 薪资
        private int age; //年纪
        private String sex; //性别
        private String area;  // 地区

    }


