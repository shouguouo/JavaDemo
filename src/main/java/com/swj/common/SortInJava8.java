package com.swj.common;


import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Java8中的排序
 * 链式调用要换行 提高可读性
 * Created by swj on 2018/1/25.
 */
public class SortInJava8 {
    private static List<User> users = Lists.newArrayList(new User("jack",17,10),
            new User("jack",18,10),
            new User("jack",19,11),
            new User("apple",25,15),
            new User("tommy",23,8),
            new User("jessica",15,13));

    /**
     * 传统的排序
     */
    public static void traditionCompareByName(){
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge()-o2.getAge();
            }
        });
        users.forEach(System.out::println);
    }

    /**
     * Java8中的排序
     */
    public static void traditionCompareByNameInJave8(){
        //users.sort((o1, o2) -> o1.getAge()-o2.getAge());
        users.sort(Comparator.comparingInt(User::getAge));
        users.forEach(System.out::println);
    }
    public static void traditionCompareByNameInJava8Reverse(){
        users.sort(Comparator.comparingInt(User::getAge).reversed());
        users.forEach(System.out::println);
    }

    /**
     * 传统的根据姓名 年龄 积分排序
     */
    public static void traditionCombinationCompare(){
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getName().equals(o2.getName())){
                    if (o1.getAge().equals(o2.getAge())){
                        return o1.getCredits() - o2.getCredits();
                    } else {
                        return o1.getAge() - o2.getAge();
                    }
                } else {
                    return o1.getName().compareTo(o2.getName());
                }
            }
        });
        users.forEach(System.out::println);
    }

    /**
     * Java8用thenComparing实现链式调用
     */
    public static void traditionCombinationCompareInJave8(){
        users.sort(Comparator.comparing(User::getName)
                .thenComparing(User::getAge, ((o1, o2) -> o2-o1))
                .thenComparing(User::getCredits));
        users.forEach(System.out::println);
    }
    public static void main(String[] args) {
        traditionCompareByName();
        System.out.println("-------------------------");
        traditionCompareByNameInJave8();
        System.out.println("-------------------------");
        traditionCompareByNameInJava8Reverse();
        System.out.println("-------------------------");
        traditionCombinationCompare();
        System.out.println("-------------------------");
        traditionCombinationCompareInJave8();
    }
}


class User{
    private String name;
    private Integer age;
    private Integer credits;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", credits=" + credits +
                '}';
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }

    public User(String name, Integer age, Integer credits) {
        this.name = name;
        this.age = age;
        this.credits = credits;
    }
}
