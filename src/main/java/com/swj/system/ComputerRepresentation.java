package com.swj.system;

/**
 * @author swj
 * @create 2018/1/31
 */
public class ComputerRepresentation {

    /**
     * (1368)(十进制) = 1*10^3 + 3*10^2 + 6*10^1 + 8*10^0;
     * (1101)(二进制) = 1*2^3 + 1*2^2 + 0*2^1 + 1*2^0
     *  不再局限于"满几进一" 我们可以重新定义每个位的权重
     *  我们还可以根据需要 规定十位是十进制 百位是八进制 也就是说 十位的权重仍然是10 而百位的权重是8*10 = 80
     *
     *  在计算机中 对于有符号的数据类型 会把最高位作为符号位 如果是1 就是负数
     *  负数通常有三种表示方法 以-15为例
     *      原码： 1000 1111
     *      反码： 1111 0000   除了符号位 按位取反
     *      补码： 1111 0001   在反码的基础上+1 好处：0和-0的表示是相同的 正负数可以直接加减 不必在做额外的转换
     *  现代计算机都是用补码来表示负数
     *
     *  //TODO 深入理解计算机系统关于进制的内容
     *
     *  递增进位制数： 最右一位是二进制 右数第二位是三进制.......右数第N位是N+1进制的
     *  故权重依次是 1 1*2 1*2*3 ...
     *  4321 = 4*4! + 3*3! + 2*2! + 1*1! = 5! - 1 =119
     *  作用：编码全排列 可以建立N个元素的全排列与自然数【1，N!】的一一对应关系
     */

    public static String oct2Bin(int a){
        boolean flag = (a < 0);
        a = Math.abs(a);
        StringBuilder str = new StringBuilder("");
        while (a > 1){
            str.append(a%2);
            a /= 2;
        }
        str.append(a);
        str = str.reverse();
        if (flag){
            // TODO 负数的转换
        }
        return str.toString();
    }
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int i = 1;
        System.out.println(i << 30);
        System.out.println(i << 31);
        System.out.println(oct2Bin(0));
    }
}
