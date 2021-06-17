package com.shouguouo.common;

/**
 * Java虚拟机在执行字节码的时候，会使用一个叫做操作数栈的数据结构来进行运算，
 * 还会在函数栈帧上保存一个叫做局部变量的结构，用来存储各个局部变量的值。
 * @author swj
 * @date 2018/2/8
 */
public class CallFunction {

    /**
     * main函数在执行的时候 会创建一个main函数的frame 记录了main中定义的局部变量i, j
     * @param args
     */
    public static void main(String[] args) {
        int i = 1;
        int j = 2;
        foo(i, j);
        System.out.println("" + i + j);
    }

    /**
     * 当main执行到foo调用时 就会创建一个属于foo函数的frame 紧跟在main的frame的后面 这里面记录了foo的两个参数i,j
     * bar销毁后又回到了foo的frame中 又对foo中的值进行操作 计算t的时候 i，j的值仍然是2,3
     * 原因就是foo在调用bar的时候是把foo中的i,j做了一次拷贝 把两个整数复制到了bar的frame中 只是对传进来的参数进行修改
     * 只能改动bar这个帧里的变量 而不影响foo里的变量
     * @param i
     * @param j
     */
    public static void foo(int i, int j){
        i ++;
        j ++;
        bar(i, j);
        int t = i + j;
    }

    /**
     * 当foo执行到bar调用时 会创建一个属于bar函数的frame 紧跟在foo之后 这里记录了bar的两个参数i,j
     * 当bar执行完之后bar所对应的frame就会销毁 bar中的变量i，j所占用的内存就都被回收了（这个说法不准确）
     * @param i
     * @param j
     */
    public static void bar(int i, int j){
        i ++;
        j ++;
        return;
    }
}
class AnotherExample{
    public static void main(String[] args) {
        int c = 1 + add(2, 3);
    }

    public static int add(int i, int j){
        return i + j;
    }
    /**
     * 编译后 查看字节码
     *  public static void main(java.lang.String[]);
     * Code:
     * 0: iconst_1
     * 1: iconst_2
     * 2: iconst_3                          // 这里先把 1,2,3分别压入到操作数栈上 位于main方法的帧里
     * // (这里的操作栈是数据结构栈 不是函数栈，函数栈在函数调用时只会创建新的栈帧，没有任何的出栈动作。)
     * 3: invokestatic  #2                  // Method add:(II)I  调用add函数 由于add方法接受两个参数就把2,3出栈了
     * 6: iadd                              // add执行完后 会把返回值再放到操作数栈里 就是操作数栈顶部
     * 7: istore_1                          // 表示取出操作数栈顶的值 并将其存入到局部变量表的第一个位置
     * 8: return
     *
     * public static int add(int, int);
     * Code:
     * 0: iload_0
     * 1: iload_1
     * 2: iadd
     * 3: ireturn
     */
}

class AboutLoopAndIf{

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        if (a < b){
            a += 3;
        }
        while (a > b){
            b ++;
        }
    }
    /**
     * 字节码
     * 0：iconst_1
     * 1：istore_1
     * 2：iconst_2
     * 3：istore_2
     * 4：iload_1
     * 5：iload_2
     * 6：if_icmpge   12   //比较操作数栈顶的两个值 如果a < b 为假 会执行第12行的内容
     * 9：iinc        1,3  // 直接更新一个局部变量 a + 3
     * 12：iload_1
     * 13：iload_2
     * 14：if_icmple 23  //比较操作数栈顶的两个值 如果a > b为假 会执行第23行内容
     * 17：iinc      2,1 //直接更新一个局部变量 b + 1；
     * 20：goto      12
     * 23：return
     */
}