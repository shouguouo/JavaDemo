package com.swj.common;


import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8中函数接口有很多
 * Function函数 Consumer消费者 Supplier提供者 Predicate 断定 四大基础函数接口 其他接口都是变种
 * 大多数是限制参数类型 数量
 * Created by swj on 2018/1/24.
 */
public class FourTypesOfInterfaces {

    /**
     * function 对接受一个T类型参数 返回R类型的结果的方法的抽象 通过调用apply方法执行内容
     * java.util.function.Function接口 使用apply（）方法
     */
    public static final int addOne(int a){
        return a+1;
    }
    public static int oper(int a, Function<Integer, Integer> action){
        return action.apply(a);
    }

    /**
     * consumer 接收一个参数 没有返回值 理解为将参数消费掉
     * java.util.function.Consumer接口 使用accept（）方法
     */
    public static final void printString(String a, Consumer<String> action){
        action.accept(a);
    }

    public static void main(String[] args) {
        System.out.println(oper(1, x -> x+1));
        printString("Hello World!", x -> System.out.println(x));
        /**
         * supplier 不接受参数 但是提供一个返回值 使用get（）方法
         * java.util.function.Supplier
         */
        Supplier<String> getInstancce = () -> "Hello,World";
        System.out.println(getInstancce.get());

        /**
         * predicate 接受一个参数 返回一个boolean值 多用于判断与过滤
         * java.util.function.Predicate
         */
        Predicate<Integer> predOdd = (x) -> x % 2 == 1;
        System.out.println(predOdd.test(7));
    }

    /**
     * Operator接口 只有两个UnaryOperator 和 BinaryOperator 属于Function接口的简写
     */


    /**
     * 提示：Java中的lambda表达式 并不是完全闭包 对值封闭 对变量不封闭 局部变量要被使用 必须声明为final或是隐式的final
     */

}
