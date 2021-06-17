package com.shouguouo.system;

/**
 * @author swj
 * @create 2018/2/1
 */
public class CounterWeight {
    /**
     * 有一架天平 有20个砝码 重量依次是1,3,9...3^19 只要被称物品在[1,(3^20-1)/2]的整数区间就可以用这个天平称
     * 思路：4的三进制是11 代表了1*3^1+1
     *       5的三进制是12 代表了1*3^1+2
     *       6的三进制是20 代表了2*3^1+0
     *       手里的砝码是3^0 ,3^1.....也就是说每个位置上是1就能直接组合出来 如果第n位上是2 则是3^(n+1)-3^n
     * @param weight 物品的重量
     * @return 称量的方案 输出格式为两组数字 第一组表示左边要放的砝码 第二组是右边的中间用空格隔开 不需要放砝码则为empty
     */
    public static int[][] count(int weight){
        int pl = 0, pr = 0;
        int poise = 1, r;
        final int LEFT = 0, RIGHT = 1;
        int[][] result = new int[2][20];
        while (weight > 0){
            r = weight % 3;
            if (r == 2){
                result[LEFT][pl++] = poise;
                weight = (weight + 1) / 3;
            }else if (r == 1){
                result[RIGHT][pr++] = poise;
                weight = (weight) / 3;
            }else {
                weight = weight / 3;
            }
            poise *= 3;
        }
        return result;
    }

    public static void main(String[] args) {
//        Stream.of(count(11)).forEach((x) -> System.out.println(Arrays.toString(x)));

        System.out.println("·".getBytes());
        System.out.println("•".getBytes());

    }
}
