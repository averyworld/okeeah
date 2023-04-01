package com.athl.gulimall.order.Steram;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//筛选员工中工资高于8000的人，并形成新的集合。 形成新集合依赖collect（收集）
public class StreamTest3 {
    public static void main(String[] args) {

        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));
        //todo 筛选员工中工资高于8000的人
        List<String> collect = personList.stream().filter(x -> x.getSalary() > 8000).map(Person::getName).collect(Collectors.toList());

        System.out.println("薪资高于8000美元的员工：" + collect);
        //获取员工薪资最高的人。
        Optional<Person> max = personList.stream().max(Comparator.comparingInt(Person::getSalary));
        System.out.println("员工薪资最大值：" + max.get().getSalary());

        //将员工的薪资全部增加1000。
        List<Person> personListNew = personList.stream().map(person -> {
            Person personNew = new Person(person.getName(), 0, 0, null, null);
            personNew.setSalary(person.getSalary() + 1000);
            return personNew;
        }).collect(Collectors.toList());
        System.out.println("一次改动前：" + personList.get(0).getName() + "-->" + personList.get(0).getSalary());
        System.out.println("一次改动后：" + personListNew.get(0).getName() + "-->" + personListNew.get(0).getSalary());
    }
}
