package com.swj.algorithms;

import java.util.Arrays;

/**
 * 计数排序
 * @author swj
 * @date 2018/2/10
 */
public class CountingSort {

    /**
     * 对于每一个待排序元素a 如果知道了待排序数组中有多少个比它小的数
     * 那么就可以直接知道在排序后的数组中a应该出现在什么位置上
     * 怎样才能快速知道比它小的数字有多少个呢 我们不能用比较的方法 那么时间复杂度还是不能降下来
     * 换一个思路 以空间换时间 若n个数的取值范围是0到n 使用一个大小为n的数组来统计从0到n 每个数在待排序数组中出现的次数
     * 被称为基于统计的排序  直方图统计
     * @param a
     */
    public void sort(int[] a){
        int len = a.length;
        final int MAX = 256;
        int[] b = new int[MAX];
        int[] c = new int[MAX];
        // 先将要排序的数据丢入c数组
        for (int i = 0; i < len; i ++){
            c[a[i]] ++;
        }
        // 计算每个值比多少个值大
        for (int i = 1; i < MAX; i ++){
            c[i] += c[i - 1];
        }
        // 赋值到b数组
        for (int i = len - 1; i >= 0; i --){
            b[c[a[i]] - 1] = a[i];
            c[a[i]] --;
        }
        for (int i = 0; i < len; i ++){
            a[i] = b[i];
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{10,9,8,2,6,5,4,3,2,1};
        new CountingSort().sort(a);
        System.out.println(Arrays.toString(a));
    }
}
