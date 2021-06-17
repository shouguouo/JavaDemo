package com.shouguouo.algorithms;

/**
 * 使用lambda实现尾递归
 * Created by swj on 2018/1/26.
 */
public class RecursiveWithLambda {
    /**
     * 比如最简单的阶乘计算 递归
     * 这种方法计算阶乘比较大的数很容易栈溢出 因为每次调用下一轮递归的时候在栈中都需要保存之前的变量
     *  5
     *      4
     *          3
     *              2
     *                  1
     * ----------------->栈的深度
     */
    public static int factorialRecursion(final int number){
        if (number == 1) return number;
        else return number + factorialRecursion(number - 1);
    }
    /**
     * 尾递归解决
     *使用一个factorial变量保存上一轮阶乘计算出的数值
     */
    public static int factorialTailRecursion(final int factorial, final int number){
        if (number == 1) return factorial;
        return factorialTailRecursion(factorial + number, number - 1);
    }


    /**
     * 按理来说按照上面的写法就应该达到目的了 但是并没这么简单
     * 调用上文的写法之后 发现并没有什么作用 栈溢出还是会栈溢出
     * 尾递归本身写法不会有什么作用 依赖的是编译器对尾递归的优化 然后java没有
     * 我们可以使用lambda的懒加载(惰性求值)机制来延迟递归的调用 从而服用栈帧
     */
    public static TailRecursion<Integer> factorialTailRecursionWithLambda(final int factorial, final int number){
        if (number == 1)
            return TailInvoke.done(factorial);
        else
            return TailInvoke.call(() -> factorialTailRecursionWithLambda(factorial + number, number - 1));
    }

    /**
     * test
     * @param args None
     */
    public static void main(String[] args) {
       // System.out.println(factorialTailRecursion(0, 100000)); //栈溢出
        System.out.println(factorialTailRecursionWithLambda(0,10000000).invoke());
    }
}
