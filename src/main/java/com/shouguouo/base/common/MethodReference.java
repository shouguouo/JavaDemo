package com.shouguouo.base.common;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.function.IntFunction;

/**
 * java8之方法引用
 * 方法引用是用来直接访问类或者实例的已经存在的方法或者构造方法 方法引用提供了一种引用而不执行方法的方式
 * 它需要由兼容的函数式接口构成目标类型上下文 计算时 方法引用会创建函数式接口的一个实例
 * 是一种更简洁易懂的lambda表达式 操作符是双冒号“::”
 * 方法引用的标准形式是：类名::方法名
 * 四种形式的方法引用：
 * 引用静态方法，引用某个对象的实例方法，引用某个类型的任意对象的实例方法，引用构造方法
 * @author swj
 * @date 2018/2/25
 */
public class MethodReference {
    public static void main(String[] args) {
        String[] stringsArray = new String[]{"xhy", "swj"};
        //通过lambda表达式来创建匿名方法
        Arrays.sort(stringsArray, (s1, s2) -> s1.compareToIgnoreCase(s2));
        //通过已经存在的方法 这种特性就叫做方法引用(Method Reference) 任意对象的实例方法引用
        Arrays.sort(stringsArray, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(stringsArray));

        Person[] pArr = new Person[]{
                new Person("003", LocalDate.of(2016,9,1)),
                new Person("001", LocalDate.of(2016,2,1)),
                new Person("002", LocalDate.of(2016,3,1)),
                new Person("004", LocalDate.of(2016,12,1))
        };
        //引用静态方法
        Arrays.sort(pArr, Person::compareByAge);
        System.out.println(Arrays.asList(pArr));

        ComparisonProvider comparisonProvider = new ComparisonProvider();
        //特定实例对象的方法引用 实例上的实例方法引用 除此之外还有超类上的实例方法引用 类型上的实例方法引用
        Arrays.sort(pArr, comparisonProvider::compareByName);
        System.out.println(Arrays.asList(pArr));

        //构造方法引用
        MyFunc myFunc = MyClass::new;
        MyClass myClass = myFunc.func(100);
        System.out.println(myClass.getValue());
        //数组构造方法引用 等价于x -> new int[x]
        IntFunction<int[]> arrayMaker = int[]::new;
        int[] array = arrayMaker.apply(10);
        System.out.println(array.length);
    }
}

interface MyFunc{
    MyClass func(int n);
}
class MyClass{
    private int val;

    MyClass(int val){
        this.val = val;
    }
    MyClass(){
        val = 0;
    }

    public int getValue(){
        return val;
    }
}
class ComparisonProvider{
    public int compareByName(Person a, Person b){
        return a.getName().compareTo(b.getName());
    }

    public int compareByAge(Person a, Person b){
        return a.getBirthday().compareTo(b.getBirthday());
    }
}
class Person{
    public Person(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    String name;
    LocalDate birthday;

    public LocalDate getBirthday() {
        return birthday;
    }

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
